package utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import java.io.*;
public class NewAPI {
	
	/**
	 * Thuoc tinh co chuc nagn format theo nam, thang, ngay
	 */
	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	
	
	
	/**
	 *  Thuoc tinh giup log thong tin ra console
	 */
	private static Logger LOGGER = Utils.getLogger(Utils.class.getName());
	/**
	 * set up connection to server
	 * @param url: Link to server need to request
	 * @param method: API protocol
	 * @param token: code need to provide to authorize user
	 * @return connection
	 * #throws IOException
	 */
	
	private static HttpURLConnection setupConnection(String url, String method, String token) throws IOException {
		//Part 1: Setup
		LOGGER.info("Request URL: " + url + "\n");
		URL line_api_url = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "Bearer" + token);
		return conn;
	}
	/**
	 * Method read responsed data from server
	 * @param conn: connection to server
	 * @return 
	 * @return 
	 * @throws IOException	
	 */
	private static  String readResponse(HttpURLConnection conn) throws IOException{
		// Read data from server
		BufferedReader in;
		String inputLine;
		
		if(conn.getResponseCode()/100 == 2) {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		
		StringBuilder response = new StringBuilder(); // using StringBuilder for optimizing memories
		while((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		response.append(inputLine + "\n")
		in.close();
		LOGGER.info("Response Info: " + response.substring(0, response.length() - 1).toString());
		return response.substring(0, response.length()-1).toString();
		
	}
	

	/**
	 * Phuong thuc goi cac API dang GET
	 * @param url, duong dan toi server request
	 * @param token: Doan ma cung cap thong tin xac thuc nguoi dung
	 * @return response: phan toi tu server(dang String)
	 * @throws Exception
	 */
	public static String get(String url, String token) throws Exception {
		
		//Part 1: Set..up
		HttpURLConnection conn = setupConnection(url, "GET", token);
		
		//Part 2: Read data response from server
		String response = readResponse(conn);
		
		return response;
	}
	
	/**
	 * Phuong thuc giup goi cac API dang POST
	 * @param url: Duong dan toi server can request
	 * @param data: Du lieu dua len server de xu ly
	 * @return response: Phan hoi tu server
	 * @throws IOException
	 */
	public static String post(String url, String data, String token) throws IOException {
		// allow PATCH PROTOCOL
		//Setup
		HttpURLConnection conn = setupConnection(url, "GET", token);
		
		// Send data
		Writer writer =  new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(data);
		writer.close();
		
		//read data from server
		String response = readResponse(conn);
		
		return response;
		
	}
	
	
	
}
