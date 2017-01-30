package com.bmshamsnahid.matching_image_puzzle;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.Telephony.TextBasedSmsColumns;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Host_Screen extends Activity implements OnClickListener{
	
	int empty_position, temp_empty_position;    //during swaping, one is for the future empty position
	public ImageView iv[] = new ImageView[10];	//image cotainer
	Bitmap bm[] = new Bitmap[10];	//image
	
	Button bt_up, bt_down, bt_left, bt_right, bt_show_image, bt_reset, bt_dismiss;	//button

    Dialog settingsDialog;	// show result, exit, restart

	/*
	Execution start here
	*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); //super class
		
		setContentView(R.layout.host_screen);   //show view
		initialize();   //initialize the componnnts
	}

	private void initialize() {
		empty_position = 0;
		bitmap_initialize();
		image_viewer_initialize();		
		setting_image_to_image_viewer();
		button_initialize();
		textbox_initialize();
	}

	private void textbox_initialize() {
		//tv_status = (TextView) findViewById(R.id.textview_status);
	}

	private void setting_image_to_image_viewer() {
		//for(int i=0; i<9; i++) iv[i].setImageBitmap(bm[i]);
		
		Random random_number = new Random();

		boolean flags[] = new boolean[10];
		for(int i=0; i<10; i++) {
			flags[i] = true;
		}
		for(int i=0; i<9; i++) {
			while(true) {
				int num = Math.abs(random_number.nextInt()) % 9;
				if(flags[num]) {
					flags[num] = false;
					//System.out.println(num);
					if(num == 8) empty_position = i;
					iv[i].setImageBitmap(bm[num]);
					break;
				}
			}
		}
		
		/*iv_zero.setImageBitmap(bm_zero);
		iv_one.setImageBitmap(bm_one);
		iv_two.setImageBitmap(bm_two);
		iv_three.setImageBitmap(bm_three);
		iv_four.setImageBitmap(bm_four);
		iv_five.setImageBitmap(bm_five);
		iv_six.setImageBitmap(bm_six);
		iv_seven.setImageBitmap(bm_seven);
		iv_eight.setImageBitmap(bm_eight);*/
	}

	private void bitmap_initialize() {
		bm[0] = BitmapFactory.decodeResource(getResources(), R.drawable.image_zero);
		bm[1] = BitmapFactory.decodeResource(getResources(), R.drawable.image_one);
		bm[2] = BitmapFactory.decodeResource(getResources(), R.drawable.image_two);
		bm[3] = BitmapFactory.decodeResource(getResources(), R.drawable.image_three);
		bm[4] = BitmapFactory.decodeResource(getResources(), R.drawable.image_four);
		bm[5] = BitmapFactory.decodeResource(getResources(), R.drawable.image_five);
		bm[6] = BitmapFactory.decodeResource(getResources(), R.drawable.image_six);
		bm[7] = BitmapFactory.decodeResource(getResources(), R.drawable.image_seven);
		bm[8] = BitmapFactory.decodeResource(getResources(), R.drawable.image_eight);
		bm[9] = BitmapFactory.decodeResource(getResources(), R.drawable.image_last);
	}

	private void button_initialize() {
		bt_up = (Button) findViewById(R.id.button_up);
		bt_down = (Button) findViewById(R.id.button_down);
		bt_left = (Button) findViewById(R.id.button_left);
		bt_right = (Button) findViewById(R.id.button_right);
		bt_show_image = (Button) findViewById(R.id.host_screen_button_show_image);
		bt_reset = (Button) findViewById(R.id.host_screen_button_reset);
		//bt_dismiss = (Button) findViewById(R.id.image_layout_dismiss);		
		
		bt_up.setOnClickListener(this);
		bt_down.setOnClickListener(this);
		bt_left.setOnClickListener(this);
		bt_right.setOnClickListener(this);
		bt_show_image.setOnClickListener(this);
		bt_reset.setOnClickListener(this);
		//bt_dismiss.setOnClickListener(this);
	}

	private void image_viewer_initialize() {
		iv[0] = (ImageView) findViewById(R.id.image_viewer_zero);
		iv[1] = (ImageView) findViewById(R.id.image_viewer_one);
		iv[2] = (ImageView) findViewById(R.id.image_viewer_two);
		iv[3] = (ImageView) findViewById(R.id.image_viewer_three);
		iv[4] = (ImageView) findViewById(R.id.image_viewer_four);
		iv[5] = (ImageView) findViewById(R.id.image_viewer_five);
		iv[6] = (ImageView) findViewById(R.id.image_viewer_six);
		iv[7] = (ImageView) findViewById(R.id.image_viewer_seven);
		iv[8] = (ImageView) findViewById(R.id.image_viewer_eight);
	}

	@Override
	public void onClick(View v) {
		try {
			temp_empty_position = empty_position;
			Bitmap bm_one, bm_two;
			Button_Click_Handler bch = new Button_Click_Handler();
			switch (v.getId()) {
			case R.id.button_up:
				empty_position = bch.up_button_click(empty_position);
				//tv_status.setText(String.valueOf(temp_empty_position) + " " + String.valueOf(empty_position));
				bm_one = ((BitmapDrawable)iv[empty_position].getDrawable()).getBitmap();
				bm_two = ((BitmapDrawable)iv[temp_empty_position].getDrawable()).getBitmap();
				iv[empty_position].setImageBitmap(bm_two);
				iv[temp_empty_position].setImageBitmap(bm_one);
				if(check_bitmap_match()) {
					//tv_status.setText("Up Clicked Matched");
					showDialog(this, "Match Over", "Congratulation You Matched All Images.");
				} 
				//else tv_status.setText("Up Clicked Not Matched");
				break;
			case R.id.button_down:
				empty_position = bch.down_button_click(empty_position);
				//tv_status.setText(String.valueOf(temp_empty_position) + " " + String.valueOf(empty_position));
				bm_one = ((BitmapDrawable)iv[empty_position].getDrawable()).getBitmap();
				bm_two = ((BitmapDrawable)iv[temp_empty_position].getDrawable()).getBitmap();
				iv[empty_position].setImageBitmap(bm_two);
				iv[temp_empty_position].setImageBitmap(bm_one);
				if(check_bitmap_match()) {
					//tv_status.setText("Down Clicked Matched");
					showDialog(this, "Match Over", "Congratulation You Matched All Images.");
				} 
				//else tv_status.setText("Ddown Clicked Not Matched");
				break;
			case R.id.button_left:
				empty_position = bch.left_button_click(empty_position);
				//tv_status.setText(String.valueOf(temp_empty_position) + " " + String.valueOf(empty_position));
				bm_one = ((BitmapDrawable)iv[empty_position].getDrawable()).getBitmap();
				bm_two = ((BitmapDrawable)iv[temp_empty_position].getDrawable()).getBitmap();
				iv[empty_position].setImageBitmap(bm_two);
				iv[temp_empty_position].setImageBitmap(bm_one);
				if(check_bitmap_match()) {
					//tv_status.setText("Left Clicked Matched");
					showDialog(this, "Match Over", "Congratulation You Matched All Images.");
				} 
				//else tv_status.setText("Left Clicked Not Matched");
				break;
			case R.id.button_right:
				empty_position = bch.right_button_click(empty_position);
				//tv_status.setText(String.valueOf(temp_empty_position) + " " + String.valueOf(empty_position));
				bm_one = ((BitmapDrawable)iv[empty_position].getDrawable()).getBitmap();
				bm_two = ((BitmapDrawable)iv[temp_empty_position].getDrawable()).getBitmap();
				iv[empty_position].setImageBitmap(bm_two);
				iv[temp_empty_position].setImageBitmap(bm_one);
				if(check_bitmap_match()) {
					//tv_status.setText("Right Clicked Matched");
					showDialog(this, "Match Over", "Congratulation You Matched All Images.");
				} 
				//else tv_status.setText("Right Clicked Not Matched");
				break;
			case R.id.host_screen_button_show_image:
					show_image_dialouge();
				break;
			case R.id.host_screen_button_reset:
				setting_image_to_image_viewer();
				break;
			}
		} catch(Exception e) {
			
		}
	}

	private boolean check_bitmap_match() {
		for(int i=0; i<9; i++) {
			Bitmap bp_temp = ((BitmapDrawable)iv[i].getDrawable()).getBitmap();
			if(equals(bm[i], bp_temp) == false) return false;
		}
		iv[8].setImageBitmap(bm[9]);
		return true;
	}
	
	public boolean equals(Bitmap bitmap1, Bitmap bitmap2) {
	    ByteBuffer buffer1 = ByteBuffer.allocate(bitmap1.getHeight() * bitmap1.getRowBytes());
	    bitmap1.copyPixelsToBuffer(buffer1);

	    ByteBuffer buffer2 = ByteBuffer.allocate(bitmap2.getHeight() * bitmap2.getRowBytes());
	    bitmap2.copyPixelsToBuffer(buffer2);

	    return Arrays.equals(buffer1.array(), buffer2.array());
	}
	
	void show_image_dialouge() {
    	settingsDialog = new Dialog(this);
    	settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    	settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.image_layout, null));
    	settingsDialog.show();
    	settingsDialog.setCanceledOnTouchOutside(true);
    }
	
	public void showDialog(Activity activity, String title, CharSequence message) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(activity);

	    if (title != null) builder.setTitle(title);

	    builder.setMessage(message);
	    
	    builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int id) {
	    		dialog.cancel();
	    		System.exit(0);
	       }
	    });
	    
	    builder.setNegativeButton("Play Again", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int id) {
	        	dialog.cancel();
	        	setting_image_to_image_viewer();
	       }
	    });
	    
	    builder.show();
	}
	
}

