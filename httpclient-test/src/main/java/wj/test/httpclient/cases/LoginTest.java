package wj.test.httpclient.cases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import wj.test.httpclient.config.TestConfig;
import wj.test.httpclient.model.InterfaceName;
import wj.test.httpclient.model.LoginCase;
import wj.test.httpclient.utils.ConfigFile;
import wj.test.httpclient.utils.DatabaseUtil;

public class LoginTest {
	
	@BeforeTest(groups="LoginTrue", description="测试准备工作")
	public void beforeTest() {
		TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
		TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
		TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
		TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
		TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
		
		TestConfig.httpClient = new DefaultHttpClient();
	}
	
	@Test(groups="loginTrue", description="用户登录成功")
	public void loginTrue() throws IOException {
		SqlSession session = DatabaseUtil.getSqlSession();
		LoginCase loginCase = session.selectOne("loginCase",1);
		System.out.println(loginCase.toString());
		System.out.println(TestConfig.loginUrl);
		
		String result = getResult(loginCase);
		
		assertEquals(result, loginCase.getExpected());
	}
	
	@Test(groups="loginFalse", description="用户登录成功")
	public void loginFalse() throws IOException {
		SqlSession session = DatabaseUtil.getSqlSession();
		LoginCase loginCase = session.selectOne("loginCase",2);
		System.out.println(loginCase.toString());
		System.out.println(TestConfig.loginUrl);
		
		String result = getResult(loginCase);
		
		assertEquals(result, loginCase.getExpected());
	}
	
	
	private String getResult(LoginCase loginCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject param = new JSONObject();
        param.put("userName",loginCase.getUserName());
        param.put("password",loginCase.getPassword());

        post.setHeader("content-type","application/json");

        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        String result;
        HttpResponse response = TestConfig.httpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");

        TestConfig.cookieStore = TestConfig.httpClient.getCookieStore();
        return  result;

    }
	
	
	
	
	/*
	 * 
	 * 添加启动spring boot工程的shell脚本
	 * 
	 * 
	source /etc/profile
	pid=$(ps x | grep "xxx.jar" | grep -v grep | awk '{print $1}')
	if [-n "$pid"]; then
	kill -9 $pid
	fi
	
	cd xxx//工程目录 - jenkins/workspace/xxx
	mvn clean package
	cd target
	pwd
	BUILD_ID=dontkillMe
	nohup java -jar xxx.jar &
	*/
	
	
	
	
	
	
	
	
	
	
	

}
