package ahang.grid;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by cwh on 16-3-7
 */
public class GridAdapter extends BaseAdapter {
	private Activity context;
	private static LayoutInflater inflater = null;
	private int itemHeight;
	private View.OnClickListener clickGrid;
	private boolean[] cis;

	private String rolatePos(int pos) {
		int start = pos / 7 * 7;
		int off = 6 - pos % 7;
		return String.valueOf(start + off);
	}

	public GridAdapter(final Activity context) {
		this.context = context;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		itemHeight = DeviceUtils.getScreenSize(context)[0] / 7;
		clickGrid = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				view.setBackgroundColor(context.getResources().getColor(R.color.ci_two));
				TextView tv_pos = (TextView) view.findViewById(R.id.pos);
				int pos = (int) tv_pos.getTag();
				cis[pos] = !cis[pos];
				renderItem((TextView) view, cis[pos], pos);
			}

		};
		cis = new boolean[53 * 7];
	}

	@Override
	public int getCount() {
		return 53 * 7;
	}

	@Override
	public Object getItem(int i) {
		return cis[i];
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	static class ViewHolder {
		LinearLayout gridWrap;
		TextView itemPos;
	}

	private void renderItem(TextView textView, boolean heavy, int pos) {
		if (heavy) {
			textView.setBackgroundColor(context.getResources().getColor(R.color.ci_two));
			textView.setText(rolatePos(pos));
		}
		else {
			textView.setBackgroundColor(context.getResources().getColor(R.color.ci_one));
			textView.setText("");
		}
	}

	@Override
	public View getView(int i, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.grid_item, parent, false);
			vh = new ViewHolder();
			vh.gridWrap = (LinearLayout) convertView.findViewById(R.id.grid_wrap);
			vh.itemPos = (TextView) convertView.findViewById(R.id.pos);
			convertView.setTag(vh);
		} else vh = (ViewHolder) convertView.getTag();

		renderItem(vh.itemPos, cis[i], i);
		vh.itemPos.getLayoutParams().height = itemHeight;
		vh.itemPos.setOnClickListener(clickGrid);
		vh.itemPos.setTag(i);

		return convertView;
	}
}
