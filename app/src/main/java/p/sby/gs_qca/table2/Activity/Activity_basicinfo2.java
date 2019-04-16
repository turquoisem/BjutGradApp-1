package p.sby.gs_qca.table2.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import p.sby.gs_qca.Main.Activity.global_variance;
import p.sby.gs_qca.R;
import p.sby.gs_qca.database.SQLiteHelper;
import p.sby.gs_qca.util.DownloadUtil;

public class Activity_basicinfo2 extends AppCompatActivity {
    private Button t2_confirm;
    private String temp;
    private String downloadurl="http://117.121.38.95:9817/platform/file/filemessage/download.ht";
    private SQLiteHelper dbhelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t2basicinfo);
        dbhelper=new SQLiteHelper(this,"BookStore.db",null,2);

        /*****上方功能栏****/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_t2bi); //主页上方功能条
        toolbar.setTitle("研究生考试试卷规范性评价表");

        toolbar.setTitleTextColor(getResources().getColor(R.color.white)); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //返回上级页面
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//返回
            }
        });

        String sessionid;
        global_variance myssession = ((global_variance)getApplicationContext());
        sessionid =myssession.getSessionid();

        Thread Getfile = new Thread(){

            @Override
            public void run() {
                super.run();


                DownloadUtil.get().download(downloadurl, sessionid,"download", new DownloadUtil.OnDownloadListener() {
                    @Override
                    public void onDownloadSuccess() {
                        System.out.println("下载完成");
                    }
                    @Override
                    public void onDownloading(int progress) {
//                        progressBar.setProgress(progress);
                    } @Override
                    public void onDownloadFailed() {
                        System.out.println("下载失败");
                    } }
                    );

            }
        };




        /*****提交按钮点击事件*******/
        //绑定按钮
        t2_confirm=(Button) findViewById(R.id.t2_confirm);

        //添加监听事件
        t2_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交确认信息

                //跳转到评分页面
//                startActivity(new Intent(Activity_basicinfo2.this,Activity_t2score.class));
//                Getfile.start();
//                dbhelper.getWritableDatabase();
                SQLiteDatabase db=dbhelper.getReadableDatabase();
                ContentValues values= new ContentValues();
                values.put("name","The Da Vinci Code");
                values.put("author","Dan Brown");
                values.put("pages",457);
                values.put("price",16.96);
                db.insert("Book",null,values);
                values.clear();
                values.put("name","The Lost Symbol");
                values.put("author","Don Brown");
                values.put("pages",511);
                values.put("price",19.95);
                db.insert("Book",null,values);
                System.out.println("ok");
            }
        });
    }
}
