import javax.swing.JComboBox;

import jm.constants.*;
public class MyScales extends JComboBox<String> implements Scales{
	private final static String[] scales = {"AEOLIAN_SCALE ", "BLUES_SCALE", "CHROMATIC_SCALE", "DIATONIC_MINOR_SCALE ", "DORIAN_SCALE ", "HARMONIC_MINOR_SCALE", "INDIAN_SCALE", "LYDIAN_SCALE", "MAJOR_SCALE", "MELODIC_MINOR_SCALE", "MINOR_SCALE", "MIXOLYDIAN_SCALE", "NATURAL_MINOR_SCALE", "PENTATONIC_SCALE", "TURKISH_SCALE"};
	private final static int[][] scaleInts ={AEOLIAN_SCALE , BLUES_SCALE, CHROMATIC_SCALE, DIATONIC_MINOR_SCALE , DORIAN_SCALE , HARMONIC_MINOR_SCALE, INDIAN_SCALE, LYDIAN_SCALE, MAJOR_SCALE, MELODIC_MINOR_SCALE, MINOR_SCALE, MIXOLYDIAN_SCALE, NATURAL_MINOR_SCALE, PENTATONIC_SCALE, TURKISH_SCALE};
	
	public MyScales() {
		super(scales);
	}
	
	public static void main(String[] args) {
		new MyScales();
	}
	
	public String getScaleString(int a) {
		return scales[a];
	}
	
	public int[] getScaleInts(int a) {
		return scaleInts[a];
	}
	

}
