import javax.swing.JComboBox;

import java.awt.Checkbox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Action extends JComboBox<String>{
	private final List<Character> alphabet = Arrays.asList(new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'});
	private List<Character> scaleLetters;
	private ArrayList<Character> triggers;
	private ArrayList<Checkbox> list;
	private int[] scale;
	
	/**
	 * constructor
	 * @param scaleSize
	 */
	public Action(int scaleSize) {
		super(new String[]{"Randomize Pan", "Randomize Volume", "Randomize Note Rhythm Value", "Next Note = Root", "Next Note down an Octave", "Next Note up an Octave"});
		List<Character> newAlph = new ArrayList<Character>();
		triggers = new ArrayList<Character>();
		this.list = new ArrayList<Checkbox>();
		for(int i = 0; i < alphabet.size(); i++) {
			list.add(new Checkbox(""+alphabet.get(i), false));
			if(i<scaleSize)
				newAlph.add(alphabet.get(i));
		}
		for(int i = 0; i< list.size(); i++)
			list.get(i).setName(list.get(i).getLabel());
		scaleLetters = newAlph;
	}
	
	/**
	 * copy Constructor
	 * @param other
	 * @param scale
	 */
	public Action(Action other, int[] scale) {
		super(new String[]{"Randomize Pan", "Randomize Volume", "Randomize Note Rhythm Value", "Move to Root", "Move down an Octave", "Move up an Octave"});
		List<Character> newAlph = new ArrayList<Character>();
		triggers = other.triggers;
		this.list = other.list;
		for(int i= 0; i< scale.length; i++) {
			newAlph.add(alphabet.get(i));
		}
		scaleLetters = newAlph;
		this.scale=scale;
		this.setSelectedIndex(other.getSelectedIndex());
	}
	
	/**
	 * returns the alphabet as a String Array
	 * @return
	 */
	public String[] alphaStringArray() {
		String[] alpha = new String[alphabet.size()];
		for(int i = 0; i< alpha.length; i++)
			alpha[i] = ""+alphabet.get(i);
		return alpha;
	}
	
	/**
	 * returns the characters that trigger the action to happen
	 * @return
	 */
	public List<Character> getTriggers(){
		return triggers;
	}
	
	/**
	 * returns the check boxes
	 * @return
	 */
	public ArrayList<Checkbox> getBoxes(){
		return list;
	}
	
	/**
	 * removes a character from the list of characters that trigger the action
	 * @param trig
	 */
	public void removeTrigger(char trig) {
		int i = triggers.indexOf(trig);
		if(i>0) {
			triggers.remove(triggers.indexOf(trig));
			list.get(list.indexOf(trig)).setState(false);
		}
	}
	
	
	/**
	 * Adds a character to the list of trigger characters that cause the action to occur.
	 * @param trig
	 */
	public void addTrigger(char trig) {
		int i = triggers.indexOf(trig);
		if(i<0) {
			triggers.add(trig);
			list.get(alphabet.indexOf(trig)).setState(true);
		}
	}
	
	
	/**
	 * returns the characters corresponding to the scale
	 * @return
	 */
	public List<Character> getCharScale(){
		return scaleLetters;
	}
	
	/**
	 * returns the scale
	 * @return
	 */
	public int[] getScale() {
		return scale;
	}
	
	
	
	
}
