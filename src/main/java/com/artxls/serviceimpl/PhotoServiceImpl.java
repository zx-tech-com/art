package com.artxls.serviceimpl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.artxls.common.exception.BusinessExceptionUtils;
import com.artxls.common.response.ReturnCode;
import com.artxls.common.util.Constant;
import com.artxls.common.util.FileUtils;
import com.artxls.dao.PhotoMapper;
import com.artxls.entity.BackPage;
import com.artxls.entity.Photo;
import com.artxls.entity.PhotoExample;
import com.artxls.entity.PhotoExample.Criteria;
import com.artxls.service.PhotoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	private PhotoMapper photoMapper;
	
	private final static String PHOTO_PATH = "photo"+File.separator ;
	
	@Override
	public void add(Photo photo,MultipartFile img) {
		String absoluteBasePath = Constant.ABSOLUTE_BASE_PATH + PHOTO_PATH;
		String relativePath = FileUtils.saveFile(absoluteBasePath, img);
		photo.setUrl(relativePath);
		if(photo.getWtype()==0) {
			photo.setWname("作品");
		}else {
			photo.setWname("相册");
		}
		
		try {
			photoMapper.insertSelective(photo);
		} catch (Exception e) {
			FileUtils.removeFile(relativePath);
			BusinessExceptionUtils.throwBusinessException(ReturnCode.DATA_OPERATION_ERROR);//再抛出去
		}
	}

	@Override
	public List<Photo> list(Integer infoId,Integer wtype,Integer subType,BackPage page, Integer beginYear, Integer endYear,String name) {
		
		PhotoExample example = new PhotoExample();
		Criteria criteria = example.createCriteria().andInfoIdEqualTo(infoId);
		if(wtype == 1)//类型查询
			criteria.andWtypeEqualTo(1);//相册
		else
			criteria.andWtypeEqualTo(wtype);//作品
		
		if(subType != null)//子类型
			criteria.andSubtypeEqualTo(subType);
		
		if(!StringUtils.isEmpty(name))//名字模糊查询
			criteria.andWnameLike(name);
		
		if(beginYear != null) {//时间查询
			endYear = endYear == null ? beginYear + 1 : endYear;
			criteria.andBginYearBetween(beginYear, endYear);
		}
		
		PageHelper.orderBy(" create_time DESC");
		Page<Object> temp = PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		List<Photo> list = photoMapper.selectByExample(example);
		page.setTotalCount((int) temp.getTotal());
		return list;
	}

	@Override
	public void remove(Integer photoId) {
		photoMapper.deleteByPrimaryKey(photoId);
	}

	@Override
	public void update(Photo photo,MultipartFile img) {
		
		String relativePath = null;
		String oldPath = null;
		if(img != null) {
			oldPath = photoMapper.selectByPrimaryKey(photo.getId()).getUrl();
			String absoluteBasePath = Constant.ABSOLUTE_BASE_PATH + PHOTO_PATH;
			relativePath = FileUtils.saveFile(absoluteBasePath, img);
			photo.setUrl(relativePath);
		}
		if(photo.getWtype()==0) {
			photo.setWname("作品");
		}else {
			photo.setWname("相册");
		}
		try {
			photoMapper.updateByPrimaryKeySelective(photo);
		} catch (Exception e) {
			if(img != null)
				FileUtils.removeFile(relativePath);
			BusinessExceptionUtils.throwBusinessException(ReturnCode.DATA_OPERATION_ERROR);//再抛出去
			return;
		}
		
		if(img != null)
			FileUtils.removeFile(oldPath);
	}

	@Override
	public void delete(Integer id) {
		String oldPath =  photoMapper.selectByPrimaryKey(id).getUrl();
		photoMapper.deleteByPrimaryKey(id);
		FileUtils.removeFile(oldPath);
	}

}
