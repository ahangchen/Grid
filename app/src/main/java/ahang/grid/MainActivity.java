package ahang.grid;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private GridView grids;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		grids = (GridView)findViewById(R.id.grids);
		grids.setAdapter(new GridAdapter(this));
	}

}
