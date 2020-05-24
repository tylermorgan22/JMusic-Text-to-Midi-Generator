import java.util.ArrayList;
import jm.constants.Durations;
import jm.music.data.Note;
import jm.music.data.Rest;

public class CharAndString {
	//stores the notes
	private ArrayList<Note> note;
	//used to keep the info about the scale
	Action choice;
	//volume of each note
	private int volume;
	//how far to transpose upward
	private int shift;
	
	/**
	 * constructor
	 * @param line
	 * @param choices
	 * @param r
	 * @param volume
	 * @param pan
	 * @param scale
	 * @param shift
	 */
	public CharAndString(String line, ArrayList<Action> choices, FileReader r, int volume, int pan, int[] scale, int shift){
		note = new ArrayList<Note>();
		this.volume = volume;
		this.shift = shift;
		makeNote(line, choices,  r, pan, scale);
		choice = choices.get(0);
		
	}
	
	/**
	 * simpler Constructor
	 * @param file
	 * @param volume
	 * @param act
	 * @param shift
	 */
	public CharAndString(String file, int volume, Action act, int shift){
		note = new ArrayList<Note>();
		this.volume = volume;
		this.shift=shift;
		choice = act;
	}
	
	/**
	 * makes the drum notes
	 * @param file
	 * @return
	 */
	public ArrayList<Note> makeDrums(String file){
		ArrayList<Note> notes = new ArrayList<Note>();
		String[] words = file.split("(?=\\p{Upper})");
		for(String word:words) {
			int pitch;
			Note note = new Note();
			pitch = word.length()%8 + 35;
			if(pitch == 35)
				pitch = 36;
			if(pitch == 37)
				pitch = 38;
			else if (pitch == 38)
				pitch = 40;
			else if (pitch == 39)
				pitch = 41;
			else if (pitch == 40)
				pitch = 43;
			else if (pitch == 41)
				pitch = 45;
			else if (pitch == 42)
				pitch = 47;
			else
				pitch = 36;
			double rVal = (word.length()%4+1);
			note.setPitch(pitch);
			note.setRhythmValue(rVal);
			note.setPan((int)(Math.random()*128));
			volume = (int)(Math.random()*51)+77;
			note.setDynamic(volume);
			note.setDuration(rVal);
			notes.add(note);
		}
		return notes;
	}
	
	/**
	 * makes the bass notes
	 * @param file
	 * @return
	 */
	public ArrayList<Note> makeBass(String file){
		ArrayList<Note> notes = new ArrayList<Note>();
		String[] words = file.split("(?=\\p{Upper})");
		for(String word:words) {
			int pitch;
			Note note = new Note();
			if(choosePitch(choice, word, 1)>-1) {
					pitch = choosePitch(choice, word, 1);
					double rVal = (Math.random() * word.length())/8.0;
					
					note.setPitch(pitch+shift);
					note.setRhythmValue(rVal);
					volume = (int)(Math.random()*51)+77;
					note.setDynamic(volume);
					note.setDuration(rVal);
			}
			else note = new Rest();
			notes.add(note);
		}
		return notes;
	}
	
	/**
	 * makes the melody notes
	 * @param line
	 * @param choices
	 * @param r
	 * @param pan
	 * @param scale
	 * @return
	 */
	public ArrayList<Note> makeNote(String line, ArrayList<Action> choices, FileReader r, int pan, int[] scale) {
		line = line.toLowerCase();
		String[] words = line.split("[^a-zA-Z0-9']+");
		double rVal = Durations.EIGHTH_NOTE;
		for(String word:words) {
			int notePan = pan;
			int pitch = 60;
			int noteVolume = volume;
			Note newNote = new Note();
			for(int j = 0; j<choices.size(); j++) {
				Action i = choices.get(j);
				if(j == 0)
					pitch = choosePitch(i, word, 0);
				if(i.getSelectedIndex()==0)
					for(char ch:word.toCharArray())
						if(i.getTriggers().indexOf(ch)!=-1)
							notePan = (int)(Math.random()*128);
				if(i.getSelectedIndex()==1)
					for(char ch:word.toCharArray())
						if(i.getTriggers().indexOf(ch)!=-1)
							noteVolume = (int)(Math.random()*128);
				if(i.getSelectedIndex()==2)
					for(char ch:word.toCharArray())
						if(i.getTriggers().indexOf(ch)!=-1)
							rVal = (int)(Math.random()*17)/8.0;
				if(i.getSelectedIndex()==3)
					if(hasTriggers(i, word))
					pitch=scale[0]+60;
				if(i.getSelectedIndex()==4) {
					if(hasTriggers(i, word)) {
						pitch=pitch-12;
					}
				}
				if(i.getSelectedIndex()==5)
					if(hasTriggers(i, word)) {
						pitch=pitch+12;	
					}
				
				newNote.setPan(notePan);
				newNote.setLength(rVal);
				newNote.setDynamic(noteVolume);
				
				if(j==choices.size()-1 && choosePitch(i, word,0)==-1) {
					newNote = new Rest();
				}
				else newNote.setPitch(pitch);
				
				
			}
			newNote.setPitch(pitch+shift);
			note.add(newNote);
		}
		
		return note;
	}
	
	/**
	 * returns the notes
	 * @return
	 */
	public ArrayList<Note> getNote() {
		return note;
	}
	
	/**
	 * chooses a pitch for a note
	 * @param a
	 * @param line
	 * @param inst
	 * @return
	 */
	public int choosePitch(Action a, String line, int inst) {
		int pitch = -1;
		for (char ch: line.toCharArray()) {
			if(a.getCharScale().indexOf(ch)>-1) {
				if(inst == 0)
					pitch = a.getScale()[a.getCharScale().indexOf(ch)]+60;
				else if(inst == 1)
					pitch = a.getScale()[a.getCharScale().indexOf(ch)]+24;
				if(inst<3) 
					return pitch;
			}
		}
		return -1;
		
	}
	
	/**
	 * returns true if the given word has a trigger for the action
	 * @param i
	 * @param word
	 * @return
	 */
	public boolean hasTriggers(Action i, String word) {
		for(char ch : word.toCharArray())
			if(i.getTriggers().indexOf(ch)!=-1)
				return true;
		return false;
	}
}
