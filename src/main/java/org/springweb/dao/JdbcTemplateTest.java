package org.springweb.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

public class JdbcTemplateTest {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void query(){
		
		int count = jdbcTemplate.queryForObject("select count(*) from mytable",  Integer.class);
		System.out.println(count);
		
		List<Map<String,Object>> res  = jdbcTemplate.queryForList("select * from mytable");
		System.out.println(res);
		
		Map<String,Object> map = jdbcTemplate.queryForMap("select * from mytable limit 1");
		System.out.println(map);
		
		List<Map<String,Object>> res1 = jdbcTemplate.queryForList("select * from mytable where a=?", new Integer[]{2});
		System.out.println(res1);
		
		//ResultSetExtractor 查询单个结果
		Dataobject obj = jdbcTemplate.query("select * from mytable where a=?", new Integer[]{2}, new ResultSetExtractor<Dataobject>(){
			@Override
			public Dataobject extractData(ResultSet rs) throws SQLException,DataAccessException {
				Dataobject obj = new Dataobject();
				obj.setA(rs.getInt("a"));
				obj.setB(rs.getString("b"));
				return obj;
			}
		});
		System.out.println(obj);
		
		List<Dataobject> lista = jdbcTemplate.query("select * from mytable where a=?", new Integer[]{2}, new ResultSetExtractor<List<Dataobject>>(){
			@Override
			public List<Dataobject> extractData(ResultSet rs) throws SQLException,DataAccessException {
				List<Dataobject> list = new ArrayList<Dataobject>();
				while(rs.next()){
					Dataobject obj = new Dataobject();
					obj.setA(rs.getInt("a"));
					obj.setB(rs.getString("b"));
					list.add(obj);
				}
				return list;
			}
		});
		System.out.println(lista);
		
		
		//RowCallbackHandler 自定义返回结果
		final List<Dataobject> list = new ArrayList<Dataobject>();
		jdbcTemplate.query("select * from mytable where a=?", new Integer[]{2}, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Dataobject obj = new Dataobject();
				obj.setA(rs.getInt("a"));
				obj.setB(rs.getString("b"));
				list.add(obj);
			}
		});
		System.out.println(list);
		
		//RowMapper使用
		List<Dataobject> list2 = jdbcTemplate.query("select * from mytable where a=?", new Integer[]{2}, new RowMapper<Dataobject>() {
			@Override
			public Dataobject mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Dataobject obj = new Dataobject();
				obj.setA(rs.getInt("a"));
				obj.setB(rs.getString("b"));
				return obj;
			}
		});
		System.out.println(list2);
		
		//lobHandler
		
	}
	
	public void insert(){
		int insert = jdbcTemplate.update("insert into mytable values(3,'ccc')");
		System.out.println(insert);
	}
	
	public void update(){
		int upcnt = jdbcTemplate.update("update mytable set b='newb' where a=2");
		System.out.println(upcnt);
		
		int upcnt1 = jdbcTemplate.update("update mytable set b=? where a=?", new Object[]{"newbbb",2});
		System.out.println(upcnt1);
		
		jdbcTemplate.update("update mytable set b=? where a=?", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(2, 2);
				ps.setString(1, "psnewbbbb");
			}
		});
		
	}
	
	public void delete(){
		int detcnt = jdbcTemplate.update("delete from mytable where a=?", new Object[]{3});
		System.out.println(detcnt);
	}
	
	public void insertBatch(){
		final List<Object[]> args = new ArrayList<Object[]>();
		args.add(new Object[]{5,"5eee"});
		args.add(new Object[]{6,"6ffff"});
		
		int []ret = jdbcTemplate.batchUpdate("insert into mytable values(?,?)", args);
		System.out.println(ret);
		
		int []bret = jdbcTemplate.batchUpdate("", new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, (Integer) args.get(i)[0]);
				ps.setString(2, (String) args.get(i)[1]);
			}
			@Override
			public int getBatchSize() {
				return args.size();
			}
		});
		System.out.println(bret);
	}
	
	public void updataBatch(){
		final List<Object[]> args = new ArrayList<Object[]>();
		args.add(new Object[]{5,"5eee"});
		args.add(new Object[]{6,"6ffff"});
		
		int []ret = jdbcTemplate.batchUpdate("update mytable set a=? where b=?", args);
		System.out.println(ret);
		
		int []bret = jdbcTemplate.batchUpdate("", new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, (Integer) args.get(i)[0]);
				ps.setString(2, (String) args.get(i)[1]);
			}
			@Override
			public int getBatchSize() {
				return args.size();
			}
		});
		System.out.println(bret);
	}
	
	public void create_or_dropTable(){
		jdbcTemplate.execute("create table jdbctable (id int, value varchar(20)");
		jdbcTemplate.execute("drop table jdbctable");
	}
	
	//存储过程
	public void callpreparecall(){
		jdbcTemplate.execute("", new CallableStatementCallback() {
			@Override
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
	
	//主键生成策略
	public void pkGenerate(){
		//DataFieldMaxValueIncrementer
	}
}

