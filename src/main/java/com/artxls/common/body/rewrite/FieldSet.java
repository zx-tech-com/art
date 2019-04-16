package com.artxls.common.body.rewrite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * {"field1","field2","IN:field3.sub1,sub2,sub3"}
 * @author Daryl
 */
public class FieldSet implements IFieldComponent{

	private List<IFieldComponent> fieldSet = new ArrayList<IFieldComponent>();
	private String value;
	private FieldPattern pattern;
	
	public FieldSet(FieldPattern pattern,String value,String[] fields) {
		this.pattern = pattern;
		this.value = value;
		this.assembleFieldSet(fields);
	}
	
	private void assembleFieldSet(String[] fields) {
		
		for(String field : fields) {
			if(field.indexOf(".") == -1) {//不包含'.'就一定是Field的对象集合
				FieldPattern subPattern;
				if(field.startsWith("EX:"))
					subPattern = FieldPattern.EXCLUDE;
				else if(field.startsWith("IN:"))
					subPattern = FieldPattern.INCLUDE;
				else
					subPattern = this.pattern;
				String[] subFields = field.replace(subPattern.getPattern(), "").split(",");
				for(String subField : subFields) {
					addField(new Field(subPattern,subField));
				}
			}
			else {
				//解析模式,value值,子属性名
				String value;
				String subField;
				if(field.startsWith(FieldPattern.INCLUDE.getPattern())) {
					value = field.substring(field.indexOf(":") + 1,field.indexOf("."));
					subField = FieldPattern.INCLUDE.getPattern() + field.substring(field.indexOf(".") + 1);
				}
				else if(field.startsWith(FieldPattern.EXCLUDE.getPattern())) {
					value = field.substring(field.indexOf(":") + 1,field.indexOf("."));
					subField = FieldPattern.EXCLUDE.getPattern() + field.substring(field.indexOf(".") + 1);
					
				}
				else {
					try {
						value = field.substring(0,field.indexOf("."));
						subField = field.substring(field.indexOf(".") + 1);
					} catch (Exception e) {
						value = this.value;
						subField = field;
					}
				}
				String[] subFields = new String[1];
				subFields[0] = subField;
				addField(new FieldSet(this.pattern,value,subFields));
			}
		}
	}


	@Override
	public FieldPattern getFieldPattern() {
		return pattern;
	}

	@Override
	public void addField(IFieldComponent component) {
		fieldSet.add(component);
	}


	@Override
	public String getValue() {
		return value;
	}


	@Override
	public Iterator<IFieldComponent> getSubFieldComponents() {
		return fieldSet.iterator();
	}

	@Override
	public String toString() {
		return "Field [pattern=" + pattern + ", value=" + value + "]";
	}

}
