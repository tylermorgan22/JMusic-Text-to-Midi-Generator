import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import jm.music.data.Note;
import jm.music.data.Phrase;

public class FileReader{
	//Stores the file to be read
	private File file;
	
	/**
	 * constructor
	 * @param f
	 */
	public FileReader(File f) {
		file = f;
	}
	
	/**
	 * makes the melody as a phrase from the given file
	 * @param file
	 * @param choices
	 * @param pan
	 * @param volume
	 * @param scale
	 * @param shift
	 * @return
	 */
	public Phrase scanFile(File file, ArrayList<Action> choices, int pan, int volume, int[] scale, int shift) {
		Phrase phr = new Phrase(0);
		Scanner reader;
		try {
			reader = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
		while(reader.hasNextLine()) {
			String line = reader.nextLine();
			
			ArrayList<Note> note = new CharAndString(line, choices, this, pan, volume, scale, shift).getNote();
			for(Note i:note)
				phr.add(i);
		}
		return phr;
	}
	
	/**
	 * makes the bassline as a phrase from the given file
	 * @param file
	 * @param volume
	 * @param a
	 * @param shift
	 * @return
	 */
	public Phrase makeBass(File file, int volume, Action a, int shift) {
		String fileText = "";
		Scanner reader;
		try {
			reader = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
		while(reader.hasNextLine()) 
			fileText += reader.nextLine();
		Phrase phr = new Phrase();
		ArrayList<Note> note = new CharAndString(fileText, volume, a, shift).makeBass(fileText);
		for(Note n : note)
			phr.add(n);
		return phr;
	}
	
	/**
	 * makes the drum part from the given file
	 * @param file
	 * @param volume
	 * @param a
	 * @return
	 */
	public Phrase makeDrums(File file, int volume, Action a) {
		String fileText = "";
		Scanner reader;
		try {
			reader = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
		while(reader.hasNextLine()) 
			fileText += reader.nextLine();
		Phrase phr = new Phrase();
		ArrayList<Note> note = new CharAndString(fileText, volume, a, 0).makeDrums(fileText);
		for(Note n : note)
			phr.add(n);
		return phr;
	}
	
	
	
	
	
}
