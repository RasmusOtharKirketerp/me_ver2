package me_ver2;
public class EclipseTime {
	// default click = hour;
	static private double ss_click;
	static private double MAX_CLICK = 100;

	public EclipseTime() {
		ss_click = 0;
	}

	public void click() {
		clickHour();
	}

	public void clickHour() {
		ss_click += 1;
	}

	public double getSSClick() {
		return ss_click;
	}

	public double getMAX_CLICK() {
		return MAX_CLICK;
	}

}
