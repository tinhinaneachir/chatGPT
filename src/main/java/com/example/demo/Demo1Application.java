package com.example.demo;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;



@SpringBootApplication
public class Demo1Application {

	private static final String API_URL = "https://api.openai.com/v1/chat/completions";
	private static final String API_KEYS = "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";

	public static void main(String[] args) throws IOException {

		String requestBody = "{\"model\": \"gpt-3.5-turbo\"," +
				"\"messages\": [{\"role\": \"user\"," +
				"\"content\": \"reformule moi ce message en se basant sur la reponse d'utilisateur 'oui' et les creneaux libres suivant '12-13 et de 13-14' cette categorie 'disponibilite' donne moi une reponse a cet email 'Bonjour je vous envoie cette invitation pour dejeuner' !\"}]}";

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient client = httpClientBuilder.build();

		HttpPost post = new HttpPost(API_URL);

		post.addHeader("Authorization", "Bearer " + API_KEYS);
		post.addHeader("Content-Type", "application/json");
		post.setEntity(new StringEntity(requestBody));

		HttpResponse response = client.execute(post);

		HttpEntity entity = response.getEntity();
		String responseBody = EntityUtils.toString(entity);
		System.out.println(responseBody);
	}
}
