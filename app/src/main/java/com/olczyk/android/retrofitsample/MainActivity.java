package com.olczyk.android.retrofitsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    TextView textViewResult;

    private PostActionRetrofit postActionRetrofit;

    @AfterViews
    public void aVoid(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postActionRetrofit = retrofit.create(PostActionRetrofit.class);

//        getPost();
        getComments();
    }

    private void getComments() {
        Call<List<Comment>> call = postActionRetrofit.getComments("posts/2/comments");
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code " + response.code());
                    return;
                }

                List<Comment> comments = response.body();

                for(Comment comment :comments){
                    String content = "\n";
                    content += "ID: "+ comment.getId() + "\n";
                    content += "PostId: " + comment.getPostId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Content: " + comment.getText() + "\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getPost() {

        Map<String,String> parameters = new HashMap<>();
        parameters.put("userId","1");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");


//        Call<List<Post>> call = postActionRetrofit.getPosts(new Integer[]{1,2,6},null,
//                null);

        Call<List<Post>> call = postActionRetrofit.getPosts(parameters);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code" + response.code());
                    return;
                }
                List<Post> posts = response.body();
                for(Post post : posts){
                    String content = "\n";
                    content += "ID: "+ post.getId() + "\n";
                    content += "UserId: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Content: " + post.getText() + "\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
