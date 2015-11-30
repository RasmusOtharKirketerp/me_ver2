package me_ver2;

public class Screen {
	private int screen_size_x;
	private int screen_size_y;
    
	public Screen(int ss_x, int ss_y) {
		// TODO Auto-generated constructor stub
		screen_size_x = ss_x;
		screen_size_y = ss_y;
	}

	
	public int getCenterX(){
		return screen_size_x / 2;
	}
	public int getCenterY(){
		return screen_size_y / 2;
	}
}
