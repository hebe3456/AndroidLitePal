package com.dblitepal.android;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                LitePal.getDatabase();   // 在dependency里引入了LitePal
                Log.d("createDatabase", "create database");
            }
        });

        Button addData = (Button) findViewById(R.id.add_data);
        // 添加监听
        addData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Book book = new Book();
                book.setName("The Da Vinci Code");   // 字符串要用双引号！！！
                book.setAuthor("Dan Brown");
                book.setPrice(15.99);
                book.setPages(454);
                // 保存数据
                book.save();
                Log.d("addDatabase", "add database");
            }
        });

        Button queryData = (Button) findViewById(R.id.query_data);
        // 添加监听
        queryData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // 查询表中所有数据
                List<Book> books = DataSupport.findAll(Book.class);
                for (Book book: books){
                    if (book.getName() != ""){   // 这个有点儿问题
                        Log.d("MainActivity", "book name is" + book.getName());
                        // getAuthor 要是public才能调用
                        Log.d("MainActivity", "book author is" + book.getAuthor());
                        Log.d("MainActivity", "book price is" + book.getPrice());
                        Log.d("MainActivity", "book pages is" + book.getPages());
                    }else{
                        Log.d("MainActivity", "no data");
                    }
                }
            }
        });

        Button delData = (Button) findViewById(R.id.del_data);
        // 添加监听
        delData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // 两个参数，第一个：表名，第二个：约束条件
                DataSupport.deleteAll(Book.class, "price<?","100");
                Log.d("MainActivity","delete data");
            }
        });
    }
}
