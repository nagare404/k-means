package Java_Second;
import java.awt.BasicStroke;

public class MyDashStroke extends BasicStroke{
	private static float shortpattern[] = {10,15};
	private static float longpattern[] = {20,30};
	
	public MyDashStroke(float lineWidth, boolean lenth) {
		super(lineWidth, CAP_BUTT,JOIN_BEVEL,1.0f,lenthjudge(lenth),0);
	}
	
	public static float[] lenthjudge(boolean b) {
		if(b) {
			return longpattern;
		}else {
			return shortpattern;
		}
	}

}
