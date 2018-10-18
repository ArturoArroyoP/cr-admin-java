// References:
// https://memorynotfound.com/apache-httpclient-custom-http-headers-example/
// Test other PC

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CurrentWar 
{

	public static void main(String[] args) throws IOException
	{				
		GetCurrentWar();
		
		System.out.println("\n\nDone...");		
	}
	
	
	
	private static void GetCurrentWar() throws IOException
	{
		// create custom http headers for httpclient
        List<Header> defaultHeaders = Arrays.asList(new BasicHeader("X-Default-Header", "default header httpclient"));

        // setting custom http headers on the httpclient
        CloseableHttpClient httpclient = HttpClients
                .custom()
                .setDefaultHeaders(defaultHeaders)
                .build();

        try 
        {
            // setting custom http headers on the http request
            HttpUriRequest request = RequestBuilder.get()
                    .setUri("https://api.clashroyale.com/v1/clans/%232Y0L8VJ2/currentwar")
                    .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")                    
                    .setHeader("authorization: Bearer ", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjM1NjY5MTdmLWQ2YzYtNDllOS05MzFjLWZiMDZlMGMzOWFlMSIsImlhdCI6MTUzOTkwMTEwMiwic3ViIjoiZGV2ZWxvcGVyLzlhNzFiNWUxLTQ2MzItYmU2ZS1lNmM3LWJiMTc4ZmU3MzVjMiIsInNjb3BlcyI6WyJyb3lhbGUiXSwibGltaXRzIjpbeyJ0aWVyIjoiZGV2ZWxvcGVyL3NpbHZlciIsInR5cGUiOiJ0aHJvdHRsaW5nIn0seyJjaWRycyI6WyIxODkuMjE4LjIzNy45MiJdLCJ0eXBlIjoiY2xpZW50In1dfQ.7Hq-m_lqSt8gfHRhvYop7VWZV1RJl80AfSOA_CvB0nA4DYM4b8YsFaVFpNhe_CSoByZKQJxA4qimpL-SDBx1sg")
                    .build();

            System.out.println("Executing request " + request.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            String responseBody = httpclient.execute(request, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        } 
        finally 
        {
            httpclient.close();
        }

	
	}

}
