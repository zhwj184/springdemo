package org.springweb.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class NameParameterJdbcTemplateTest {
	
	@Autowired
	private NamedParameterJdbcTemplate nameParamTemplate;

	public void query(){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("va", 7);
		param.put("vb", "new888");
		int count = nameParamTemplate.update("insert into mytable values(:va,:vb)", param);
		System.out.println(count);
	}
	
}
