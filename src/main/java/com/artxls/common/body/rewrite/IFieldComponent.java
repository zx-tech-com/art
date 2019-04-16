package com.artxls.common.body.rewrite;

import java.util.Iterator;

public interface IFieldComponent{

	FieldPattern getFieldPattern();
	
	void addField(IFieldComponent component);
	
	String getValue();
	
//	Iterator<String> getParents();
	
	Iterator<IFieldComponent> getSubFieldComponents();
}
