package com.example.myapplicationtestmm;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplicationtestmm.model.Author;
import com.example.myapplicationtestmm.network.APIclient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    CustomAdapter customAdapter;
    public static List<Author>authorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gridView);
        
        authorList = new ArrayList<>();

        //make network call
        Call<List<Author>> call = APIclient.apIinterface().getAuthor();
        call.enqueue(new Callback<List<Author>>() {
            @Override
            public void onResponse(Call<List<Author>> call, Response<List<Author>> response) {
                if(response.isSuccessful()){

                    authorList =response.body();

                    customAdapter = new CustomAdapter(response.body(), MainActivity.this);
                    gridView.setAdapter(customAdapter);

                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //Intent
                            Intent intent = new Intent();
                            intent.putExtra("name", authorList.get(position).getName());
                            intent.putExtra("image",authorList.get(position).getLink());

                            startActivity(new Intent(getApplicationContext(),ItemDataActivity.class).putExtra("name", authorList.get(position).getName()).putExtra("image",authorList.get(position).getLink()));

                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(),"An error Occured " , Toast.LENGTH_LONG).show();

                }


            }

            @Override
            public void onFailure(Call<List<Author>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"An error Occured " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    public class CustomAdapter extends BaseAdapter {

        public List<Author> authorList;
        public Context context;

        public CustomAdapter(List<Author> authorList, Context context) {
            this.authorList = authorList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return authorList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.row_data,null);

            //find view
            TextView name = view.findViewById(R.id.textView);
            ImageView image = view.findViewById(R.id.imageView);

            //Set data
            name.setText(authorList.get(position).getName());
            //set Image

            Glide.with(context)
                    .load(authorList.get(position).getLink())
                    .into(image);

            return view;
        }
    }
}