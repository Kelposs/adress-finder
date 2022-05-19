

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class HRjson {
	public static Response search(String key) {

		String baseURL = "https://geoapi.heartrails.com/api/json?";
		Response result = null;
		try {

			URL url = new URL(
					baseURL + ((key.matches("\\d{7}")) ? "method=searchByPostal&postal="+key : "method=suggest&matching=like&keyword=" + URLEncoder.encode(key, StandardCharsets.UTF_8)));
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.connect();

			InputStream in = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));

			Gson gson = new Gson();
			JsonReader jr = new JsonReader(br);
			HeartRails obj = gson.fromJson(jr, HeartRails.class);
			result = obj.getResponse();

			in.close();
			br.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
