import jm.JMC;
import jm.music.data.*;
import jm.music.tools.Mod;
import jm.util.Write;
import jm.constants.Scales;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.event.*;
import javax.swing.*;

public class Composition implements JMC, ChangeListener, ActionListener, Scales {
	private JSlider volume, panner;
	private int savedVol;
	private int savedPan;
	private JButton goBtn;
	private JButton chooseBtn;
	private File file;
	private MyScales box;
	private int[] scale;
	private JLabel pan;
	private JLabel vol;
	private JFrame f;
	private JTabbedPane tabbedPane;
	private ArrayList<Action> choices;
	private JComboBox transpose;
	private int out;
	
	public static void main(String[] args) {
		new Composition();
	}
	
	/**
	 * Constructor: builds GUI
	 */
	public Composition() {
            //nums = new ScaleMaker().getScale();
            //phrase = new Phrase();
			choices = new ArrayList<Action>();
            makeGUI();
            
    }
	
	/**
	 * Makes the GUI including a 4 tabs
	 */
	private void makeGUI() {
		f = new JFrame("JMusic Text to Midi Converter");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1000, 500);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		JPanel presets = new JPanel(new BorderLayout());
		tabbedPane.add("PRESETS", presets);
		presets.setLayout(new BoxLayout(presets, BoxLayout.PAGE_AXIS));
		
        // pan
        panner = new JSlider(0, 127, 64);
		panner.setEnabled(false);
		panner.addChangeListener(this);
		presets.add(panner);
		pan = new JLabel();
		presets.add(pan);
		pan.setText("Default Pan: "+ panner.getValue());
		
		// volume
        volume = new JSlider(0, 127, 64);
		volume.setEnabled(false);
		volume.addChangeListener(this);
		presets.add(volume);
		vol = new JLabel();
		presets.add(vol);
		vol.setText("Default Vol: "+ volume.getValue());
		
		// start stop
		goBtn = new JButton("Start");
		goBtn.addActionListener(this);
		presets.add(goBtn);
		
		// file Chooser
		chooseBtn = new JButton("CHOOSE FILE");
		chooseBtn.addActionListener(this);
		presets.add(chooseBtn);
		
		box = new MyScales();
		presets.add(box);
		
		transpose = new JComboBox(new String[] {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"});
		presets.add(transpose);
		
		scale = MAJOR_SCALE;
		choices.add(new Action(scale.length));
		choices.add(new Action(scale.length));
		choices.add(new Action(scale.length));
		
		
		
				
		
        
		
		//ACTIONS tab 1
		
		JPanel actions = new JPanel();
		tabbedPane.add("ACTION 1", actions);
		//actions.setLayout(new BoxLayout(actions, BoxLayout.Y_AXIS));     
		actions.setLayout(new BoxLayout(actions, BoxLayout.X_AXIS));
		actions.add(choices.get(0));
		for(int i = 0; i<choices.get(0).getBoxes().size(); i++)
		 {
			Checkbox cBox = choices.get(0).getBoxes().get(i);
			actions.add(cBox);
		}
		
		//ACTIONS tab 2
		JPanel action2 = new JPanel();
		tabbedPane.add("ACTION 2", action2);
		//actions.setLayout(new BoxLayout(actions, BoxLayout.Y_AXIS));     
		action2.setLayout(new BoxLayout(action2, BoxLayout.X_AXIS));
		action2.add(choices.get(1));
		for(int i = 0; i<choices.get(1).getBoxes().size(); i++)
		 {
			Checkbox cBox = choices.get(1).getBoxes().get(i);
			action2.add(cBox);
		}
		
		//ACTIONS tab 3
		JPanel action3 = new JPanel();
		tabbedPane.add("ACTION 3", action3);
		//actions.setLayout(new BoxLayout(actions, BoxLayout.Y_AXIS));     
		action3.setLayout(new BoxLayout(action3, BoxLayout.X_AXIS));
		action3.add(choices.get(2));
		for(int i = 0; i<choices.get(2).getBoxes().size(); i++)
		 {
			Checkbox cBox = choices.get(2).getBoxes().get(i);
			action3.add(cBox);
		}
		
		f.add(tabbedPane);
		//tabbedPane.setVisible(true);
		f.pack();
		f.setVisible(true);
	}
	
	/**
	 * Pulls up a file chooser dialog and lets the user choose the input file.
	 * @return
	 */
	public File getFile() {
		
		FileDialog d = new FileDialog(f);
		f.setVisible(false);
		d.pack();
		d.setVisible(true);
		f.setVisible(true);
		return d.getFiles()[0];
	}
	
	
	/**
	 * Controls the text label for the Default Vol/Pan sliders
	 */
	public void stateChanged(ChangeEvent e){
        // panner slider moved
        if (e.getSource() == panner) {
           pan.setText("Default Pan: "+ panner.getValue());
        }
        
        if (e.getSource() == volume) {
            vol.setText("Default Vol: "+ volume.getValue());
        }
	}
	
	/**
	 * Controls what happens when users choose a file or start the program.
	 * Triggers the background operations that generate the midi files
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == goBtn) {
			savedVol = volume.getValue();
			savedPan = panner.getValue();
			panner.setEnabled(false);
			volume.setEnabled(false);
			goBtn.setEnabled(false);
			scale = box.getScaleInts(box.getSelectedIndex());
			//WRITE FILE
			
			box.setEnabled(false);
			FileReader reader = new FileReader(file);
			
			for(int j = 0; j< choices.size(); j++) {
				Action act = choices.get(j);
				for(Checkbox i : act.getBoxes()) {
					if(i.getState()==true)
						act.addTrigger(i.getName().charAt(0));
					else act.removeTrigger(i.getName().charAt(0));
				}
				choices.set(j, new Action(act, scale));
			}
			out = transpose.getSelectedIndex();
			transpose.setEnabled(false);
			Phrase melPhrase = reader.scanFile(file, choices, savedPan, savedVol, scale, out);
            Phrase basPhrase = reader.makeBass(file, savedVol, choices.get(0), out);
            Phrase druPhrase = reader.makeDrums(file, savedVol, choices.get(0));
            //repeatToSize(melPhrase, basPhrase, druPhrase);
            //Mod.addToDuration(basPhrase, melPhrase.getEndTime());
            
            Part melPart = new Part();
            melPart.add(melPhrase);
            Part basPart = new Part();
            basPart.add(basPhrase);
            Part druPart = new Part();
            druPart.add(druPhrase);
            
            Score sc = new Score();
            sc.setTitle("Melody");
            sc.add(melPart);
            Score basSc = new Score();
            basSc.setTitle("Bass");
            basSc.add(basPart);
            Score druSc = new Score();
            druSc.setTitle("Drums");
            druSc.add(druPart);
            saveScore(sc, basSc, druSc,"1.mid");
			return;
		}
		if(e.getSource() == chooseBtn) {
			f.setVisible(false);
			file = getFile();
			f.setVisible(true);
			panner.setEnabled(true);
			volume.setEnabled(true);
			goBtn.setEnabled(true);
			box.setEnabled(true);
			chooseBtn.setEnabled(false);
			for(int i = 0; i<choices.size(); i++)
				for(int j = 0; j < choices.get(i).getBoxes().size(); j++)
						choices.get(i).getBoxes().get(j).setEnabled(true);
			return;
		}
		
		
	}
	
	/**
	 * Writes the midi files
	 * @param melody
	 * @param bass
	 * @param drums
	 * @param fileName
	 */
	public void saveScore(Score melody, Score bass, Score drums, String fileName) {
		// write the score to a MIDIfile
		Write.midi(melody, "Melody" + fileName);
		Write.midi(bass, "Bass" + fileName);
		Write.midi(drums, "Drums" + fileName);
	}
	
	
	public void repeatToSize(Phrase p1, Phrase p2, Phrase p3) {
		Phrase p4 = p3.alias();
		while(p3.getEndTime()<p1.getEndTime()) {
			Mod.mutate(p4);
			Mod.append(p3, p4);
		}
	}
}