package com.artxls.entity;

public class BackPage {

	private Integer pageNumber;
	private Integer pageSize;
	private Integer totalCount;
	
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
	public static BackPage generatePage(Integer pageNumber,Integer pageSize) {
		BackPage page =  new BackPage();
		page.setPageNumber(pageNumber);
		page.setPageSize(pageSize);
		return page;
	}
	
}
