package tlshop.android.tianlun.com.lindong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.type;



public class TableActivity extends Activity implements OnGroupScrollListener, OnChildScrollListener {


    /**
     * 用于存放标题的id,与textview引用
     */
    List<Map<String, String>> alist = new ArrayList<Map<String, String>>();
    private SparseArray<TextView> mTitleTvArray;
    //表格部分
    private AbsCommonAdapter<TableModel> mLeftAdapter, mRightAdapter;
    private int pageNo = 0;
    private WeakHandler mHandler = new WeakHandler();

    //用于滚动
   /* @Bind(R.id.re_title_common)
    RelativeLayout re_title_common;*/

    @Bind(R.id.hoverlayout)
    SideGroupLayout mHoverLayout;
    @Bind(R.id.sidescrollview)
    SideTopScrollView mSideTopScrollView;
    //表格部分
    @Bind(R.id.tv_table_title_left)
    TextView tv_table_title_left;
    @Bind(R.id.left_container_listview)
    ListView leftListView;
    @Bind(R.id.right_container_listview)
    ListView rightListView;
    @Bind(R.id.right_title_container)
    LinearLayout right_title_container;
    @Bind(R.id.title_horsv)
    SyncHorizontalScrollView titleHorScv;
    @Bind(R.id.content_horsv)
    SyncHorizontalScrollView contentHorScv;
    @Bind(R.id.frist_gradient)
    RelativeLayout frist_gradient;

    @Bind(R.id.running_name)
    TextView textRun;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_table);
        ButterKnife.bind(this);

        initView();
        initData();



    }

    private static String name = "";


    public void initView() {
        name = getIntent().getStringExtra("name");

        if (name.equals("2")) {


            textRun.setText("动感单车数据");
        } else if (name.equals("3")) {


            textRun.setText("体能训练数据");
        } else {


            textRun.setText("跑步数据");
        }


        //实现滑动
        mHoverLayout.setOnGroupScrollListener(this);
        mSideTopScrollView.setOnChildScrollListener(this);


        //findByid();


        getLayoutInflater().inflate(R.layout.table_right_title, right_title_container);


        // 设置两个水平控件的联动
        titleHorScv.setScrollView(contentHorScv);
        contentHorScv.setScrollView(titleHorScv);

        //findTitleTextViewIds();
        initTableView();
        //setListener();


        //头部日期
        tv_table_title_left.setText("日期");
        tv_table_title_left.setTextSize(15);

    }


    public void initData() {
        setData();
    }


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(this,AboutMeActivity.class));
        return super.onOptionsItemSelected(item);
    }*/

    public void init() {

    }




    private int[] tableRightItemId = new int[]{
            R.id.tv_table_content_right_item0, R.id.tv_table_content_right_item1, R.id.tv_table_content_right_item2, R.id.tv_table_content_right_item3, R.id.tv_table_content_right_item4,
            R.id.tv_table_content_right_item5, R.id.tv_table_content_right_item6, R.id.tv_table_content_right_item7, R.id.tv_table_content_right_item8, R.id.tv_table_content_right_item9, R.id.tv_table_content_right_item10,
            R.id.tv_table_content_right_item11, R.id.tv_table_content_right_item12, R.id.tv_table_content_right_item13, R.id.tv_table_content_right_item14
    };

    public void initTableView() {
        mLeftAdapter = new AbsCommonAdapter<TableModel>(this, R.layout.table_left_item, null) {
            @Override
            public void convert(AbsViewHolder helper, TableModel item, List<String> list, int pos) {
                TextView tv_table_content_left = helper.getView(R.id.tv_table_content_item_left);
                tv_table_content_left.setText(item.getLeftTitle());
                tv_table_content_left.setTextSize(15);
                int textcolr = Color.rgb(85, 85, 85);
                tv_table_content_left.setTextColor(textcolr);
                if (pos % 2 == 0) {
                    //被2整除
                    tv_table_content_left.setBackgroundColor(Color.WHITE);
                } else {
                    int u = Color.rgb(247, 247, 247);
                    tv_table_content_left.setBackgroundColor(u);
                }
            }
        };


        mRightAdapter = new AbsCommonAdapter<TableModel>(this, R.layout.table_right_item, null) {
            @Override
            public void convert(AbsViewHolder helper, TableModel item, List<String> list, final int pos) {

                for (int i = 0; i < tableRightItemId.length; i++) {
                    TextView tv = (TextView) helper.getView(tableRightItemId[i]);
                    tv.setTag(pos);
                    setTextColor(tv);
                    tv.setText(list.get(i));
                    tv.setOnClickListener(new TextViewOnClickListener(pos));
                }
               /* for (int i = 0; i < 15; i++) {
                  View view = ((LinearLayout) helper.getConvertView()).getChildAt(i);
                    view.setVisibility(View.VISIBLE);
              }*/
            }
        };
        leftListView.setAdapter(mLeftAdapter);
        rightListView.setAdapter(mRightAdapter);
    }

    public void setTextColor(TextView tv_table_content_right_item) {
        int i = (Integer) tv_table_content_right_item.getTag();
        int textcolr = Color.rgb(85, 85, 85);
        tv_table_content_right_item.setTextColor(textcolr);
        if (i % 2 == 0) {
            //被2整除
            int u = Color.rgb(225, 255, 255);
            tv_table_content_right_item.setBackgroundColor(Color.WHITE);
            tv_table_content_right_item.setTextSize(15);

        } else {
            int u = Color.rgb(247, 247, 247);
            tv_table_content_right_item.setBackgroundColor(u);
            tv_table_content_right_item.setTextSize(15);

        }

    }

    public class TextViewOnClickListener implements View.OnClickListener {
        private int poistion;

        public TextViewOnClickListener(int poistion) {
            this.poistion = poistion;
        }

        @Override
        public void onClick(View view) {

            TextView tv = (TextView) view;
            Toast.makeText(view.getContext(), poistion + "---" + tv.getText(), Toast.LENGTH_LONG).show();
        }
    }


    public void setData() {
        doGetDatas(0, RefreshParams.REFRESH_DATA);
    }

    //模拟网络请求
    public void doGetDatas(int pageno, int state) {
        //固定栏模拟数据 日期
        ArrayList<String> array_data = new ArrayList<String>();
        for (int i = 1; i <= 32; i++) {
            if (i < 10) {
                array_data.add("10.0" + i);
            } else {
                array_data.add("10." + i);
            }

        }
        Log.e("array_data", array_data.size() + "");
        List<OnlineSaleBean> onlineSaleBeanList = new ArrayList<>();
        for (int i = 0 + pageno * 20; i <= 31; i++) {
            onlineSaleBeanList.add(new OnlineSaleBean("" + array_data.get(i)));
        }
        Log.e("onlineSaleBeanList", onlineSaleBeanList.size() + "");
        if (state == RefreshParams.REFRESH_DATA) {

        } else {

        }
        setDatas(onlineSaleBeanList, state);
    }


    private void setDatas(List<OnlineSaleBean> onlineSaleBeanList, int type) {

        HashMap<String, String> mapp;

        for (int i = 1; i <= 32; i++) {
            mapp = new HashMap<String, String>();
            mapp.put("wj1", "1列" + "," + i + "行");

            mapp.put("wj2", "2列" + "," + i + "行");
            mapp.put("wj3", "3列" + "," + i + "行");
            mapp.put("wj4", "4列" + "," + i + "行");
            mapp.put("wj5", "5列" + "," + i + "行");
            mapp.put("wj6", "6列" + "," + i + "行");


            alist.add(mapp);
        }
        if (onlineSaleBeanList.size() > 0) {
            List<TableModel> mDatas = new ArrayList<>();
            for (int i = 0; i < onlineSaleBeanList.size(); i++) {
                OnlineSaleBean onlineSaleBean = onlineSaleBeanList.get(i);
                TableModel tableMode = new TableModel();
                tableMode.setOrgCode(onlineSaleBean.getOrgCode());
                tableMode.setLeftTitle(onlineSaleBean.getCompanyName());
             /*   tableMode.setText0(onlineSaleBean.getOrgCode() + "wangjh" + alist.get(i).get("wang"));//列0内容
                tableMode.setText1(onlineSaleBean.getAreaName() + "" + alist.get(i).get("li"));//列1内容
                tableMode.setText2(onlineSaleBean.getSaleAll() + "");//列2内容
                tableMode.setText3(onlineSaleBean.getSaleAllOneNow() + "");
                tableMode.setText4(onlineSaleBean.getSaleAllLast() + "");
                tableMode.setText5(onlineSaleBean.getSaleAllOneNowLast() + "");//
                tableMode.setText6(onlineSaleBean.getSaleAllRate() + "wangjian");//
                tableMode.setText7(onlineSaleBean.getSaleAllOneNowRate() + "");//
                tableMode.setText8(onlineSaleBean.getRetailSale() + "");//
                tableMode.setText9(onlineSaleBean.getRetailSaleOneNow() + "");//
                tableMode.setText10(onlineSaleBean.getRetailSaleLast() + "");//
                tableMode.setText11(onlineSaleBean.getRetailSaleOneNowLast() + "");//
                tableMode.setText12(onlineSaleBean.getRetailSaleRate() + "");//
                tableMode.setText13(onlineSaleBean.getRetailSaleOneNowRate() + "");//
                tableMode.setText14(onlineSaleBean.getOnlineSale() + "");//*/
                tableMode.setText0("" + alist.get(i).get("wj1"));//列0内容
                tableMode.setText1("" + alist.get(i).get("wj2"));//列1内容
                tableMode.setText2("" + alist.get(i).get("wj3"));//列2内容
                tableMode.setText3("" + alist.get(i).get("wj4"));
                tableMode.setText4("" + alist.get(i).get("wj5"));
                tableMode.setText5("" + alist.get(i).get("wj6"));//
                tableMode.setText6(onlineSaleBean.getSaleAllRate() + "");//
                tableMode.setText7(onlineSaleBean.getSaleAllOneNowRate() + "");//
                tableMode.setText8(onlineSaleBean.getRetailSale() + "");//
                tableMode.setText9(onlineSaleBean.getRetailSaleOneNow() + "");//
                tableMode.setText10(onlineSaleBean.getRetailSaleLast() + "");//
                tableMode.setText11(onlineSaleBean.getRetailSaleOneNowLast() + "");//
                tableMode.setText12(onlineSaleBean.getRetailSaleRate() + "");//
                tableMode.setText13(onlineSaleBean.getRetailSaleOneNowRate() + "");//
                tableMode.setText14(onlineSaleBean.getOnlineSale() + "");//
                mDatas.add(tableMode);
            }
            boolean isMore;
            if (type == RefreshParams.LOAD_DATA) {
                isMore = true;
            } else {
                isMore = false;
            }
            mLeftAdapter.addData(mDatas, isMore);
            mRightAdapter.addData(mDatas, isMore);
            //加载数据成功，增加页数
            pageNo++;
//            if (mDatas.size() < 20) {
//                pulltorefreshview.setLoadMoreEnable(false);
//            }
            mDatas.clear();
        } else {
            //数据为null
            if (type == RefreshParams.REFRESH_DATA) {
                mLeftAdapter.clearData(true);
                mRightAdapter.clearData(true);
                //显示数据为空的视图
                //                mEmpty.setShowErrorAndPic(getString(R.string.empty_null), 0);
            } else if (type == RefreshParams.LOAD_DATA) {
                Toast.makeText(this, "请求json失败", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public boolean isChildScroll() {
        return mHoverLayout != null && mHoverLayout.isScrollToEnd();
    }

    @Override
    public boolean isGroupScroll() {
        return mSideTopScrollView != null && mSideTopScrollView.isScrollToTop();
    }

    @Override
    public void onScrollChanged(int left, int top) {
    }

    //月份菜单
    Calendar calendar = Calendar.getInstance();







    //状态栏
    @TargetApi(19)
    private static void setTranslucentStatus(Activity activity, boolean on) {

        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {

            winParams.flags |= bits;

        } else {

            winParams.flags &= ~bits;

        }
        win.setAttributes(winParams);
    }
}