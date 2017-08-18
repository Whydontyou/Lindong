package tlshop.android.tianlun.com.lindong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbsCommonAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;
    protected App app;
    private String type;
    public AbsCommonAdapter(Context context, int itemLayoutId, App app, String type) {
        this.type=type;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mItemLayoutId = itemLayoutId;
        this.app = app;
        mDatas = new ArrayList<T>();
    }

    public AbsCommonAdapter(Context context, int itemLayoutId, String type) {
        this(context, itemLayoutId, null, type);
    }

    public void addItemData(T mBean, boolean isRefresh) {

        mDatas.add(mBean);
        if (isRefresh) {
            notifyDataSetChanged();
        }
    }

    public void addItemData(T mBean, int index, boolean isRefresh) {

        mDatas.add(index, mBean);
        if (isRefresh) {
            notifyDataSetChanged();
        }
    }

    public void addItemData(Collection<? extends T> mCommonbeans, int index, boolean isRefresh) {

        mDatas.addAll(index, mCommonbeans);
        if (isRefresh) {
            notifyDataSetChanged();
        }
    }

    public void addItemData(Collection<? extends T> mCommonbeans, boolean isRefresh) {

        mDatas.addAll(mCommonbeans);
        if (isRefresh) {
            notifyDataSetChanged();
        }
    }

    public void addData(List<T> mCommonbeans, boolean isMore) {

        if (isMore) {
            if (mCommonbeans != null) {

                mDatas.addAll(mCommonbeans);
            }
        } else {

            mDatas.clear();
            if (mCommonbeans != null) {
                mDatas.addAll(mCommonbeans);
            }
        }
        notifyDataSetChanged();
    }

    public void remove(int pos) {
        if (mDatas != null && mDatas.size() > 0) {
            mDatas.remove(pos);
            notifyDataSetChanged();
        }
    }

    public void clearData(boolean clear) {
        if (clear) {
            if (mDatas != null && mDatas.size() > 0) {
                mDatas.clear();
                notifyDataSetChanged();
            }
        }
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        if(mDatas!= null &&position>=0&&position<=(mDatas.size()-1)){
            return mDatas.get(position);
        }
        return null ;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final AbsViewHolder viewHolder = getViewHolder(
                position, convertView, parent
        );

        List<String> list = new ArrayList<>();

        TableModel tableModel = (TableModel) getItem(position);
        list.add(tableModel.getText0());
        list.add(tableModel.getText1());
        list.add(tableModel.getText2());
        list.add(tableModel.getText3());
        list.add(tableModel.getText4());
        list.add(tableModel.getText5());
        list.add(tableModel.getText6());
        list.add(tableModel.getText7());
        list.add(tableModel.getText8());
        list.add(tableModel.getText9());
        list.add(tableModel.getText10());
        list.add(tableModel.getText11());
        list.add(tableModel.getText12());
        list.add(tableModel.getText13());
        list.add(tableModel.getText14());

        convert(viewHolder,getItem(position), list, position);

        return viewHolder.getConvertView();

    }

    public abstract void convert(AbsViewHolder helper,T item, List<String> list, int pos);

    private AbsViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return AbsViewHolder.get(
                mContext, convertView, parent, mItemLayoutId, position
        );
    }

    public List<T> getDatas() {
        return mDatas;
    }

}
