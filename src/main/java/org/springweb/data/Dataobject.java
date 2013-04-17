package org.springweb.data;

public class Dataobject{
	private Integer a;
	private String b;
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public Integer getA() {
		return a;
	}
	public void setA(Integer a) {
		this.a = a;
	}
	@Override
	public String toString() {
		return "a:" + a + " b:" + b;
	}
	
}
