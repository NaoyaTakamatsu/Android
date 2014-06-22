package com.naosho.pairbear;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dbtestfutureenka.database.WarikanEntity;

public class WarikanAdapter extends ArrayAdapter<WarikanEntity> {

	private LayoutInflater inflater;

	public WarikanAdapter(Context context, int resource,
			List<WarikanEntity> objects) {
		super(context, 0, objects);
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Viewholder holder;
		if (convertView == null) {
			convertView = inflater
					.inflate(R.layout.list_warikan, parent, false);
			holder = new Viewholder();
			holder.textDate = (TextView) convertView
					.findViewById(R.id.textDate);
			holder.textName = (TextView) convertView
					.findViewById(R.id.textName);
			holder.textPrise = (TextView) convertView
					.findViewById(R.id.textPrise);
			convertView.setTag(holder);
		} else {
			holder = (Viewholder) convertView.getTag();
		}
		WarikanEntity entity = getItem(position);
		holder.textDate.setText(entity.getDay());
		holder.textName.setText(entity.getName());
		holder.textPrise.setText("" + entity.getPrice());
		return convertView;
	}
}

class Viewholder {
	TextView textDate;
	TextView textName;
	TextView textPrise;

}