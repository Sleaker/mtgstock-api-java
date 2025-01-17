package org.api.mtgstock.tools;

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;

import lombok.extern.log4j.Log4j2;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

@Log4j2
public class URLUtilities {
	private CloseableHttpClient httpclient;
	private HttpClientContext httpContext;
	private BasicCookieStore cookieStore;

	public URLUtilities() {
		httpclient = HttpClients.custom().setUserAgent(MTGStockConstants.USER_AGENT_VALUE).setRedirectStrategy(new LaxRedirectStrategy()).build();
		httpContext = new HttpClientContext();
		cookieStore = new BasicCookieStore();
		httpContext.setCookieStore(cookieStore);
	}
	
	public String extractAndClose(HttpResponse response) throws IOException
	{
		var ret = EntityUtils.toString(response.getEntity());
		EntityUtils.consume(response.getEntity());
		return ret;
	}
	
	public HttpResponse execute(HttpRequestBase req) throws IOException
	{
		return httpclient.execute(req,httpContext);
	}
	
	public HttpResponse doGet(String url) throws IOException
	{
		log.debug("Parsing url " + url);
		var getReq = new HttpGet(url);
		return execute(getReq);
	}

	public JsonElement extractJson(String url) throws IOException {
		var reader = new JsonReader(new InputStreamReader(doGet(url).getEntity().getContent()));
		JsonElement e= JsonParser.parseReader(reader);
		reader.close();
		return e;
		
		
	}


}
