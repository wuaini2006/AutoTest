package wj.test.httpclient.config;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

public class TestConfig {
	public static String loginUrl;
	public static String updateUserInfoUrl;
	public static String getUserListUrl;
	public static String getUserInfoUrl;
	public static String addUserUrl;

	
	public static DefaultHttpClient httpClient;
	public static CookieStore cookieStore;
}
