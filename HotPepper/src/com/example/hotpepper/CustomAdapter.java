package com.example.hotpepper;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter{

	private List<Summary> mList;
	private LayoutInflater mInflater;
	private Context context;

	/*
	 * 使用するViewの専用クラス
	 */
	static class ViewHolder{
		TextView name;
		TextView genre;
		TextView coupon;
	}

	public CustomAdapter(Context context, List<Summary> data) {
		// TODO 自動生成されたコンストラクター・スタブ
		super();
		this.context = context;
		this.mList = data;
		//inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		mInflater = LayoutInflater.from(context);
	}

	/*
	 * 配列の大きさを返す。
	 */
	@Override
	public int getCount() {
		// TODO 自動生成されたメソッド・スタブ
		return mList.size();
	}

	/*
	 * 要素を返す。
	 */
	@Override
	public Object getItem(int position) {
		// TODO 自動生成されたメソッド・スタブ
		return mList.get(position);
	}
	/*
	 *IDを返す。
	 */
	@Override
	public long getItemId(int position) {
		// TODO 自動生成されたメソッド・スタブ
		return position;
	}


	/*
	 * getViewが呼ばれるたびに、viewに値が設定される。
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自動生成されたメソッド・スタブ
		ViewHolder holder;
		Summary summary = (Summary)getItem(position);

		//最初の1回だけInflate
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.list_low,parent, false);

			holder = new ViewHolder();
			holder.name = (TextView)convertView.findViewById(R.id.Name);
			holder.genre = (TextView)convertView.findViewById(R.id.Genre);
			holder.coupon = (TextView)convertView.findViewById(R.id.Coupon);

			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}

		/*
		 * TextViewへの描画処理
		 */
		holder.name.setText(summary.getsName());
		holder.coupon.setText(summary.getsCoupon());
		holder.genre.setText(summary.getsGenre());

		return convertView;

	}
}