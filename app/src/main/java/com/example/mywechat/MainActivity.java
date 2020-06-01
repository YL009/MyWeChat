package com.example.mywechat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    private Fragment mTab01=new weixinFragment();
    private Fragment mTab02=new frdFragment();
    private Fragment mTab03=new contactFragment();
    private Fragment mTab04=new settingsFragment();

    private TextView title;
    private RecyclerView detail;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<UserAccount>userAccounts;

    FragmentManager fm;

    private LinearLayout mTabWeixin;
    private LinearLayout mTabFrd;
    private LinearLayout mTabAddress;
    private LinearLayout mTabSettings;

    private ImageButton mImgWeixin;
    private ImageButton mImgFrd;
    private ImageButton mImgAddress;
    private ImageButton mImgSettings;
    private ImageButton add;

    private static final String TAG = MainActivity.class.getSimpleName();

    private List<String> mList = new ArrayList<>();

    private List<StickyData> mDataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        userAccounts=new ArrayList<>();
        myAdapter=new MyAdapter(this,userAccounts);
        recyclerView=findViewById(R.id.recycleview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);

        initView();
        initFragment();
        selectfragment(0);
        intEvent();

        initList();
        initData();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1= LayoutInflater.from(MainActivity.this).inflate(R.layout.dialogview,null,false);
                final EditText edusername=view1.findViewById(R.id.ed_username);
                final EditText edpassword=view1.findViewById(R.id.ed_password);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("用户信息输入")
                        .setView(view1)
                        .setPositiveButton("添加", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                UserAccount userAccount=new UserAccount();
                                userAccount.setUsername(edusername.getText().toString().trim());
                                userAccount.setPassword(edpassword.getText().toString().trim());
                                userAccounts.add(userAccount);
                                myAdapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("取消",null).show();
            }
        });
    }

    private void initList() {
        mList.add("路德维希·凡·贝多芬|德国作曲家、钢琴家、指挥家，被称为乐圣。");
        mList.add("萧友梅|中国专业音乐教育的奠基人和开拓者、音乐理论家、作曲家。");
        mList.add("阿炳|民间音乐家、二胡演奏家，誉为演奏能手。");
        mList.add("冼星海|中国近代作曲家、钢琴家--人民音乐家。");
        mList.add("聂耳|中国音乐家--时代歌手。");
        mList.add("施光南|誉为时代歌手，现代抒情歌曲作曲家。");
        mList.add("弗里德里克·肖邦|誉为钢琴诗人，波兰作曲家、钢琴家。");
        mList.add("罗伯特·舒曼|德国著名作曲家、音乐评论家。");
        mList.add("莫扎特|奥地利作曲家，被誉为神童。");
        mList.add("约瑟夫·海顿|奥地利作曲家，维也纳古典派奠基者之一。");
        mList.add("舒伯特|奥地利作曲家--前所未有的最富诗意的音乐家。");
        mList.add("巴赫|德国最伟大的古典作曲家之一，管风琴演奏家。");
        mList.add("弗仑兹·李斯特|天才的匈牙利作曲家、钢琴家、指挥家和音乐活动家。");
        mList.add("约翰奈斯·勃拉姆斯|德国十九世纪后半叶最卓越的、古典乐派最后的一位作曲家。");
        mList.add("门德尔松|德国著名作曲家。");
    }

    private void initData() {
        for (int i = 0; i < mList.size(); i++) {
            StickyData bean = new StickyData();
            String s = mList.get(i);
            String musician = s.substring(0, s.indexOf("|"));
            String achievement = s.substring(s.indexOf("|") + 1, s.length());
            bean.setArea(musician);
            bean.setTeam(achievement);
            mDataList.add(bean);
        }

        Log.d(TAG, "initData: " + mDataList.size());
    }

    private void initView() {
        mTabWeixin=(LinearLayout)findViewById(R.id.id_tab_weixin);
        mTabFrd=(LinearLayout)findViewById(R.id.id_tab_frd);
        mTabAddress=(LinearLayout)findViewById(R.id.id_tab_contact);
        mTabSettings=(LinearLayout)findViewById(R.id.id_tab_settings);

        mImgWeixin=(ImageButton)findViewById(R.id.id_tab_weixin_img);
        mImgFrd=(ImageButton)findViewById(R.id.id_tab_frd_img);
        mImgAddress=(ImageButton)findViewById(R.id.id_tab_contact_img);
        mImgSettings=(ImageButton)findViewById(R.id.id_tab_settings_img);
        add=(ImageButton)findViewById(R.id.add);

        title=(TextView)findViewById(R.id.tv_sticky_header_view);
        detail=(RecyclerView)findViewById(R.id.rcv_sticky);

        StickyAdapter adapter = new StickyAdapter(this);

        RecyclerView rcvSticky = findViewById(R.id.rcv_sticky);
        final TextView tvArea = findViewById(R.id.tv_sticky_header_view);

        rcvSticky.setLayoutManager(new LinearLayoutManager(this));
        rcvSticky.setHasFixedSize(true);
        rcvSticky.setAdapter(adapter);

        adapter.setStickyDataList(mDataList);

        rcvSticky.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View stickyInfoView = recyclerView.findChildViewUnder(
                        tvArea.getMeasuredWidth() / 2, 5);
                if (stickyInfoView != null && stickyInfoView.getContentDescription() != null) {
                    tvArea.setText(String.valueOf(stickyInfoView.getContentDescription()));
                }
                View transInfoView = recyclerView.findChildViewUnder(
                        tvArea.getMeasuredWidth() / 2, tvArea.getMeasuredHeight() + 1);
                if (transInfoView != null && transInfoView.getTag() != null) {
                    int transViewStatus = (int) transInfoView.getTag();
                    int dealtY = transInfoView.getTop() - tvArea.getMeasuredHeight();
                    if (transViewStatus == StickyAdapter.HAS_STICKY_VIEW) {
                        if (transInfoView.getTop() > 0) {
                            tvArea.setTranslationY(dealtY);
                        } else {
                            tvArea.setTranslationY(0);
                        }
                    } else if (transViewStatus == StickyAdapter.NONE_STICKY_VIEW) {
                        tvArea.setTranslationY(0);
                    }
                }
            }
        });
    }
    private void initFragment(){
        fm=getFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        transaction.add(R.id.id_content,mTab01);//第一个参数为int型
        transaction.add(R.id.id_content,mTab02);
        transaction.add(R.id.id_content,mTab03);
        transaction.add(R.id.id_content,mTab04);
        transaction.commit();
    }

    private void intEvent(){
        mTabWeixin.setOnClickListener(this);
        mTabFrd.setOnClickListener(this);
        mTabAddress.setOnClickListener(this);
        mTabSettings.setOnClickListener(this);
    }

    private void hidefragment(FragmentTransaction transaction){
        transaction.hide(mTab01);
        transaction.hide(mTab02);
        transaction.hide(mTab03);
        transaction.hide(mTab04);
    }

    private void selectfragment(int i){
        FragmentTransaction transaction=fm.beginTransaction();
        hidefragment(transaction);
        switch (i){
            case 0:
                transaction.show(mTab01);
                mImgWeixin.setImageResource(R.drawable.tab_weixin_pressed);
                break;
            case 1:
                transaction.show(mTab02);
                mImgFrd.setImageResource(R.drawable.tab_find_frd_pressed);
                break;
            case 2:
                transaction.show(mTab03);
                mImgAddress.setImageResource(R.drawable.tab_address_pressed);
                break;
            case 3:
                transaction.show(mTab04);
                mImgSettings.setImageResource(R.drawable.tab_settings_pressed);
                break;
            default:
                break;
        }
        transaction.commit();
    }


    public void onClick(View v){//点击响应
        resetimg();
        switch (v.getId()){
            case R.id.id_tab_weixin:
                selectfragment(0);
                title.setVisibility(View.GONE);
                detail.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                break;
            case R.id.id_tab_frd:
                selectfragment(1);
                title.setVisibility(View.VISIBLE);
                detail.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                break;
            case R.id.id_tab_contact:
                selectfragment(2);
                title.setVisibility(View.GONE);
                detail.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                break;
            case R.id.id_tab_settings:
                selectfragment(3);
                title.setVisibility(View.GONE);
                detail.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                break;
            default:
                title.setVisibility(View.GONE);
                detail.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                break;
        }
    }

    public void resetimg(){
        mImgWeixin.setImageResource(R.drawable.tab_weixin_normal);
        mImgFrd.setImageResource(R.drawable.tab_find_frd_normal);
        mImgAddress.setImageResource(R.drawable.tab_address_normal);
        mImgSettings.setImageResource(R.drawable.tab_settings_normal);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

