package idv.PN_Wu.WebPageCharacterSetTester;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Main {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet request = new HttpGet("http://sitcon.org/");
		HttpResponse httpResponse = httpClient.execute(request);
		HttpEntity entity = httpResponse.getEntity();

		String strResult = EntityUtils.toString(entity);
		// System.out.println(strResult);

		for (String strEncoding : Charset.availableCharsets().keySet()) {
			for (String strDecoding : Charset.availableCharsets().keySet()) {
				try {
					byte[] bytes = strResult.getBytes(strEncoding);
					String temp = new String(bytes, strDecoding);
					if (temp.indexOf("學生計算機年會 | Students' Information Technology Conference") != -1)
						System.out.printf("%s to %s\n", strEncoding, strDecoding);
				} catch (Exception e) {
					// TODO: handle exception
					// System.out.printf("Error: %s to %s.\n", strEncoding, strDecoding);
				}
			}
		}

	}

}
