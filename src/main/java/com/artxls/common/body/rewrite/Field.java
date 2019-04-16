package com.artxls.common.body.rewrite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.artxls.common.exception.BusinessExceptionUtils;
import com.artxls.common.response.ReturnCode;

public class Field implements IFieldComponent{

	private FieldPattern pattern;
	private String value;
	
	public Field(FieldPattern pattern,String value) {
		this.pattern = pattern;
		this.value = value;
	}
	
	@Override
	public FieldPattern getFieldPattern() {
		return pattern;
	}

	@Override
	public void addField(IFieldComponent component) {
		BusinessExceptionUtils.throwBusinessException(ReturnCode.UNSUPPORTED_OPERATION);
	}

	@Override
	public String getValue() {
		return value;
	}


	
	/**
	 * 返回只包含自己的迭代器
	 */
	@Override
	public Iterator<IFieldComponent> getSubFieldComponents() {
		List<IFieldComponent> fieldSet = new ArrayList<IFieldComponent>();
		fieldSet.add(this);
		return fieldSet.iterator();
	}

	@Override
	public String toString() {
		return "Field [pattern=" + pattern + ", value=" + value + "]";
	}

	
	
}
