package com.majiang.community.provider;

import com.alibaba.fastjson.JSON;
import com.majiang.community.dto.AccessTokenDTO;
import com.majiang.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;
import java.io.IOException;


@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        OkHttpClient client = new OkHttpClient();

        String accessTokenjson  = JSON.toJSONString(accessTokenDTO);

        RequestBody body =RequestBody.create(MediaType.parse("application/json"), accessTokenjson);

        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            String token = string.split("&")[0].split("=")[1];;
            return token;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=")
                .header("Authorization"," token "+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
