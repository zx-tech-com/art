package com.artxls.common.body.rewrite;

public enum FieldPattern {
	
	
	INCLUDE("IN:"),EXCLUDE("EX:");
	
	private String pattern;
	private FieldPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getPattern() {
		return pattern;
	}
}
