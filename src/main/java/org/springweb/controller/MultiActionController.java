package org.springweb.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * 方法名的匹配可以通过：InternalPathMethodNameResolver，PropertiesMethodNameResolver，ParameterMethodNameResolver
 * @author weijian.zhongwj
 *
 */
public class MultiActionController extends org.springframework.web.servlet.mvc.multiaction.MultiActionController{

	///processA.do
	public ModelAndView processA(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("index");
		model.addObject("message", "MultiActionController Web Project + Spring 3 MVC - welcome()");
		return model;
	}
	
	///list.do
	public Map list(HttpServletRequest request,HttpServletResponse response){
		Map map = new HashMap();
		map.put("message", "multiActionContontroller list");
		return map;
	}

}
