package org.springweb.controller;

import org.springframework.web.multipart.MultipartFile;

public class Student{
	private String name;
	private String sex;
//	private MultipartFile file;//加这个支持文件上传
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "name:" + name + " sex:" + sex;
	}
	
}
