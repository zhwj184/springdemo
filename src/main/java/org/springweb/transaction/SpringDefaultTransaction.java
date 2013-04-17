package org.springweb.transaction;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.ibatis.sqlmap.engine.datasource.DbcpDataSourceFactory;

public class SpringDefaultTransaction {

	public static void main(String[] args) {
		
		//编程式事务管理
		//PlatformTransactionManager
		DataSource dataSource = new DbcpDataSourceFactory().getDataSource();
		DefaultTransactionDefinition tran = new DefaultTransactionDefinition();
		tran.setTimeout(200);
		PlatformTransactionManager manager = new DataSourceTransactionManager(dataSource);
		TransactionStatus status = manager.getTransaction(tran);
		
		try{
			//处理业务
		}catch(Exception e){
			manager.rollback(status);
		}
		finally{
			manager.commit(status);
		}
		
		//transTemplate
		TransactionTemplate transTemplate = null;
		transTemplate.execute(new TransactionCallback() {
			
			@Override
			public Object doInTransaction(TransactionStatus arg0) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		
		//声明式事务管理
//		common-datasource.xml
		
		//注解
		
		//ThradLocal 多数据源切换处理
		
		//Strategy 适配器模式
	}
}
