package org.springweb.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;


public class MyIbatisTest extends SqlMapClientDaoSupport{

	public void query(){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("a", 2);
		List<Dataobject> list = this.getSqlMapClientTemplate().queryForList("test.query", param);
		System.out.println(list);
	}
	
	public void insert(){
		Dataobject da = new Dataobject();
		da.setA(100);
		da.setB("100ddd");
		long id = (Long) this.getSqlMapClientTemplate().insert("test.insert", da);
		System.out.println(id);
	}
	
	public void update(){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("a", 2);
		param.put("b", "ibatisb");
		int cnt = this.getSqlMapClientTemplate().update("test.update", param);
		System.out.println(cnt);
	}
	
	public void delete(){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("a", 2);
		param.put("b", "ibatisb");
		int cnt = this.getSqlMapClientTemplate().delete("test.delete", param);
		System.out.println(cnt);
	}
	
	public void batchInsert(){
		final List<Dataobject> list = new ArrayList<Dataobject>();
		Dataobject da = new Dataobject();
		da.setA(101);
		da.setB("100ddd");
		list.add(da);
		Dataobject da1 = new Dataobject();
		da1.setA(102);
		da1.setB("100ddd");
		list.add(da1);
		
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {

			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for(Dataobject data: list){
					executor.insert("insert", data);
				}
				executor.executeBatch();
				return null;
			}
		});
	}
}
