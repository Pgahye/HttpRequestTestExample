package com.jx372.httprequesttestexample.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.GsonBuilder;
import com.jx372.httprequesttestexample.R;
import com.jx372.httprequesttestexample.core.domain.guestBook;
import com.jx372.httprequesttestexample.network.SafeAsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onFatchGuextbookClick(View view) {

       new FetchGuestbookAsyncTask().execute();



    }
     //통신결과를 담을 result class
    private class JSONResultFetchGuestBookList{

         private String result;
         private String message;
         private List<guestBook> data;


         public String getResult() {
             return result;
         }

         public void setResult(String result) {
             this.result = result;
         }

         public String getMessage() {
             return message;
         }

         public void setMessage(String message) {
             this.message = message;
         }

         public List<guestBook> getData() {
             return data;
         }

         public void setData(List<guestBook> data) {
             this.data = data;
         }
     }

    // 통신내부 클래스 api 하나당 하나씩

    private class FetchGuestbookAsyncTask extends SafeAsyncTask<List<guestBook>>{

        @Override
        public List<guestBook> call() throws Exception{
            //1. 요청셋팅
            String url="http://192.168.1.34:8088/mysite03/guestbook/api/list";
            HttpRequest request = HttpRequest.get(url);


            // name=안대혁 , no = 1
            // request.contentType(HttpRequest.CONTENT_TYPE_FORM);

            //"{name:안대혁,no:1}"
            //request.contentType(HttpRequest.CONTENT_TYPE_JSON);

            request.accept(HttpRequest.CONTENT_TYPE_JSON);
            request.connectTimeout(3000);

            request.readTimeout(3000);

            //2.요청
            int responseCode = request.code();

            //3.응답처리
            if(responseCode != HttpURLConnection.HTTP_OK){

                // 오류처리

                throw new RuntimeException("Http Response Error : " + responseCode);


            }

            //4.  json을 사용한 객체 생성
            Reader reader = request.bufferedReader();

            JSONResultFetchGuestBookList jsonResult =
            new GsonBuilder().create().fromJson(reader,JSONResultFetchGuestBookList.class );


            //5 결과에러 체크

            if("fail".equals(jsonResult.getResult())){


                throw new RuntimeException(jsonResult.getMessage());
            }

            return jsonResult.getData();
        }

        @Override
        protected void onException(Exception e) throws RuntimeException {

            Log.e("FetchGuestbookAsyncTask", "Exception" +e);
            super.onException(e);
        }

        @Override
        protected void onSuccess(List<guestBook> list) throws Exception {
            super.onSuccess(list);

            //결과처리
            for(guestBook guestbook : list){

                System.out.println(list);
            }

        }



    }


}
