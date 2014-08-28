package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class Main
{
	// プログラムの開始
	public static void main(String args[])
	{
		new Main();
	}
	
	public Main()
	{
		String apiUrl = "http://weather.livedoor.com/forecast/webservice/json/v1?city=";
		
		System.out.print("地域IDを入力（東京 : 130010） : ");
		
		try
		{
			// 入力文字を取る
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String city = input.readLine();
			
			// パラメータ付与
			apiUrl += city;
			
			// HTTPクライアント作成
			HttpURLConnection client = null;
			// URL生成
			URL url = new URL(apiUrl);
			
			client = (HttpURLConnection) url.openConnection();
			client.setRequestMethod("GET");
			
			if (client.getResponseCode() == HttpURLConnection.HTTP_OK)
			{
				InputStreamReader isr = new InputStreamReader(client.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                
                String line = "";
                String r = "";
                while ((line = reader.readLine()) != null)
                {
                	r += line;
                }
                Gson gson = new Gson();
                WeatherData data = gson.fromJson(r.toString(),WeatherData.class);
                System.out.print(data.forecasts.get(0).dateLabel +"の"+data.title+"は"+data.forecasts.get(0).telop);
			}
		}
		catch(Exception ex)
		{
			System.out.print("エラーがでました");
		}
	}
}
