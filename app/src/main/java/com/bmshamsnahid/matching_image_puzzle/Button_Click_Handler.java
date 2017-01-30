package com.bmshamsnahid.matching_image_puzzle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.Button;
import android.widget.ImageView;

public class Button_Click_Handler {
	
	public int left_button_click(int empty_position) {
		if(empty_position == 2 || empty_position == 5 || empty_position == 8) {
			return empty_position;
		} else {
			empty_position++;
			return empty_position;
		}
	}
	
	public int right_button_click(int empty_position) {
		if(empty_position == 0 || empty_position == 3 || empty_position == 6) {
			return empty_position;
		} else {
			empty_position--;
			return empty_position;
		}
	}
	
	public int up_button_click(int empty_position) {
		if(empty_position == 6 || empty_position == 7 || empty_position == 8) {
			return empty_position;
		} else {
			//Bitmap bm_one, bm_two;
			//Host_Screen hs = new Host_Screen();
			
			empty_position += 3;
			return empty_position;
		}
	}
	
	public int down_button_click(int empty_position) {
		if(empty_position == 0 || empty_position == 1 || empty_position == 2) {
			return empty_position;
		} else {
			empty_position -= 3;
			return empty_position;
		}
	}
	
	public void swap_image_view(int pos1, int pos2) {
		ImageView iv_one, iv_two;
		
		for(int i=0; i<10; i++) {}
		Bitmap bm_one, bm_two, bm_temp;
		//bm_one = ((BitmapDrawable)iv_one.getDrawable()).getBitmap();
		//bm_two = ((BitmapDrawable)iv_two.getDrawable()).getBitmap();
		//iv_one.setImageBitmap(bm_two);
		//iv_two.setImageBitmap(bm_one);
	}
	
	public void swap_button_click() {
		
	}

}
