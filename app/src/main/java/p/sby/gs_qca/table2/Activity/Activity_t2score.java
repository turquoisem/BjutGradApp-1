package p.sby.gs_qca.table2.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;

import es.dmoral.toasty.Toasty;
import p.sby.gs_qca.Main.Activity.Activity_list;
import p.sby.gs_qca.Main.Activity.Activity_login;
import p.sby.gs_qca.R;
import p.sby.gs_qca.table2.Fragment.t2CommentsFragment;
import p.sby.gs_qca.table2.Fragment.t2ScoreFragment;
import p.sby.gs_qca.widget.NumRangeInputFilter100;

public class Activity_t2score extends AppCompatActivity {
    private FragmentTabHost mTabHost;
    private ViewPager mViewPager;
    private List<Fragment> mFragmentList;
    private Class mClass[] = {t2ScoreFragment.class,t2CommentsFragment.class};
    private Fragment mFragment[] = {new t2ScoreFragment(),new t2CommentsFragment()};
    private String mTitles[] = {"评分项目","专家评语"};
    private int mImages[] = {
            R.drawable.tab_score,
            R.drawable.tab_comments
    };
    public String option="";
    public String institute="";
    public String coursename="";
    public String teacher="";
    public String classroom="";
    public String papernum="";
    public String courseid="";

    public String t2_score1="";
    public String t2_score2="";
    public String t2_score3="";
    public String t2_score4="";
    public String t2_score5="";
    public String t2_score6="";
    public String t2_score7="";
    public String t2_score8="";
    public String t2_comment="";







    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t2_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.t2_main_toolbar);
        toolbar.setTitle("研究生考试试卷规范性评价表");
        setSupportActionBar(toolbar);

        init();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Intent intent=getIntent();

        option="basic";
        institute= intent.getStringExtra("institute");
        coursename=intent.getStringExtra("coursename");
        teacher=intent.getStringExtra("teacher");
        classroom=intent.getStringExtra("classroom");
//        time=intent.getStringExtra("time");
        courseid=intent.getStringExtra("courseid");
        papernum=intent.getStringExtra("papernum");
        courseid=intent.getStringExtra("courseid");
        System.out.println("在课堂信息页打印id:"+courseid);





    }


    private void init() {

        initView();

        initEvent();
    }

    private void initView() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        mFragmentList = new ArrayList<Fragment>();

        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0;i < mFragment.length;i++){
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTitles[i]).setIndicator(getTabView(i));
            mTabHost.addTab(tabSpec,mClass[i],null);
            mFragmentList.add(mFragment[i]);
            mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
        }

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {


                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });
    }

    private View getTabView(int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.t2_tabitem, null);

        ImageView image = (ImageView) view.findViewById(R.id.t2c_image);
        TextView title = (TextView) view.findViewById(R.id.t2s_title);

        image.setImageResource(mImages[index]);
        title.setText(mTitles[index]);



        return view;
    }

    private void initEvent() {

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                mViewPager.setCurrentItem(mTabHost.getCurrentTab());
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabHost.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.tb2_preview) {
            Toast.makeText(this, "你点击了 预览按钮！", Toast.LENGTH_SHORT).show();
            System.out.println(t2_score1);
            System.out.println(t2_score2);
            System.out.println(t2_score3);
            System.out.println(t2_comment);

            Intent intent=new Intent(Activity_t2score.this,Activity_t2preview.class);
            intent.putExtra("option",option);
            intent.putExtra("institute",institute);
            intent.putExtra("coursename",coursename);
            intent.putExtra("teacher",teacher);
            intent.putExtra("classroom",classroom);
            intent.putExtra("papernum",papernum);
            intent.putExtra("courseid",courseid);

            intent.putExtra("score1",t2_score1);
            intent.putExtra("score2",t2_score2);
            intent.putExtra("score3",t2_score3);
            intent.putExtra("score4",t2_score4);
            intent.putExtra("score5",t2_score5);
            intent.putExtra("score6",t2_score6);
            intent.putExtra("score7",t2_score7);
            intent.putExtra("score8",t2_score8);
            intent.putExtra("comment",t2_comment);

            startActivity(intent);
        }


        else if (id == R.id.tb2_quit) {
            startActivity(new Intent(this,Activity_list.class));
        }

        return super.onOptionsItemSelected(item);
    }




    /***********语音听写模块函数*************/

    public void initSpeech() {
        //1.创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(Activity_t2score.this, null);
        //2.设置accent、language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        //3.设置回调接口
        mDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean isLast) {
                if (!isLast) {
                    //解析语音
                    //返回的result为识别后的汉字,直接赋值到TextView上即可
                    String result = parseVoice(recognizerResult.getResultString());

                }
            }

            @Override
            public void onError(SpeechError speechError) {

            }
        });
        //4.显示dialog，接收语音输入
        mDialog.show();

    }

    /**
     * 解析语音json
     */
    public String parseVoice(String resultString) {
        Gson gson = new Gson();
        Voice voiceBean = gson.fromJson(resultString, Voice.class);

        StringBuffer sb = new StringBuffer();
        ArrayList<Voice.WSBean> ws = voiceBean.ws;
        for (Voice.WSBean wsBean : ws) {
            String word = wsBean.cw.get(0).w;
            sb.append(word);
        }
        return sb.toString();
    }
    /**
     * 语音对象封装
     */
    public class Voice {

        public ArrayList<WSBean> ws;

        public class WSBean {
            public ArrayList<CWBean> cw;
        }

        public class CWBean {
            public String w;
        }
    }

    private void setFilter() {
//        totalScore.setFilters(new InputFilter[]{new NumRangeInputFilter100()});
    }

}
