package com.bmshamsnahid.matching_image_puzzle;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Drag_And_Drop extends Activity implements View.OnTouchListener {

	private ImageView mImageView;
	private ViewGroup mRrootLayout;
	private int _xDelta;
	private int _yDelta;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.host_screen);
		/*mRrootLayout = (ViewGroup) findViewById(R.id.root);
		mImageView = (ImageView) mRrootLayout.findViewById(R.id.imageView);*/

		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
		mImageView.setLayoutParams(layoutParams);
		mImageView.setOnTouchListener(this);
	}

	public boolean onTouch(View view, MotionEvent event) {
		final int X = (int) event.getRawX();
		final int Y = (int) event.getRawY();
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
			_xDelta = X - lParams.leftMargin;
			_yDelta = Y - lParams.topMargin;
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			break;
		case MotionEvent.ACTION_POINTER_UP:
			break;
		case MotionEvent.ACTION_MOVE:
			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
					.getLayoutParams();
			layoutParams.leftMargin = X - _xDelta;
			layoutParams.topMargin = Y - _yDelta;

			view.setLayoutParams(layoutParams);
			break;
		}
		mRrootLayout.invalidate();
		return true;
	}
}