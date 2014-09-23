package com.example.maventest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import com.example.maventest.ui.page.ValidatePage;

public class WicketApplication extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		// TODO 自動生成されたメソッド・スタブ
		return ValidatePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();

		try {
			Context context=new InitialContext();
			Context envContext = (Context)context.lookup("java:comp/env");
		    DataSource ds =(DataSource)envContext.lookup("jdbc/PoolDatasource");
			
			Connection conn = ds.getConnection();

			Statement st = conn.createStatement();

			String sql = "select * from test";
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// add your configuration here
	}

}
