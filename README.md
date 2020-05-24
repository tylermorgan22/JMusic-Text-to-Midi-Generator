# JMusic-Text-to-Midi-Generator
This program lets the user choose a text File and set parameters for the generation of 3 new Midi file based off of the text File.

EXAMPLES:

BM1.mp3: song made with BM1.txt as input.

BM2.mp3: song made with BM2.txt as input.

BM3.mp3: song made with BM3.txt as input. My personal favorite of the three


SETUP:
1. Follow these instructions from JMusic:
https://explodingart.com/jmusic/GetjMusic.html
2. Ensure that the JMusic files are in your external Archives and the Inst files are in your build path
3. Put my java files into the src folder

OPERATION:
1. Run the project: 

CLICKED_START.PNG

2. Press "CHOOSE FILE":

CLICKED_FILE_CHOOSER.PNG

3. Choose a text file
4. Choose a default pan and vol setting on the sliders and  a key and a scale in the bottom where it says "C" and "AEOLEAN_SCALE:"

SET_PAN_VOL_SCALE_KEY.PNG

5. In each of the three ACTION tabs, choose something that you want to happen to the melody when the scanner hits a word with the characters represented by check boxes:

ACTION_OPTIONS.PNG

6. Use the checkboxes to select trigger characters for the specified actions:

ACTION_CHOSEN.PNG

7. Press start. The output files will be rendered to Drums1.mid, Melody1.mid, and Bass1.mid

WHAT TO DO WITH THE OUTPUT FILES:

A. In REAPER, or another DAW,  set up 3 new tracks:
  1. Sampler: Choose the sound for the melody. Add a Reverb effect. Import Melody1.mid.
  2. Sampler: Choose a bass sound, add a bass amp simulator if the sound is not already amplified or "electronic" sounding.
    Add a Reverb effect. Import Bass1.mid.
  3. Drum Machine: I use this - https://www.dskmusic.com/dsk-drumz-akoustik/ - CharAndString.java is set to make notes in the pitch range of this VST instrument, C2-B2. Also add a Reverb effect to this plugin. Import Drums1.mid
  
B. Loop the shorter files so that they line up with the longest one

  ATTRIBUTION:
  
    JMUSIC Developers: Andrew Brown, Andrew Sorensen, Adam Kirby, Rene Wooller, Tim Opie
    
    Dropdown Menu Tutorial
    https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html
    A large part of my UI is dropdown menus so that users can choose the scale and customize the computer's algorithm.
    
    Slider Tutorial
    https://docs.oracle.com/javase/tutorial/uiswing/components/slider.html
    Sliders are important because they can give me control of the volume and pan of the midi notes
    
    Tabbed Pane Tutorial
    https://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html
    Tabbed panes allow me to have a more complex GUI without a large, confusing window

 TESTING PROCESS:
    I did a lot of minor testing along the way. I used a text file with the string "ah bh ch dh eh fh gh hai" to test all of the Actions and the melody writer. I used my notes from AP European history as another testing file. I used this file to print into the console all of the strings that I scanned to make sure the RegEx that I used worked. For an example of what the program can produce, I have submitted what I made with 3 different sections of the Bee Movie.
   
   JAVA CLASSES:

MyScales: a dropdown menu to choose the key of the song

Action: a dropdown menu with checkboxes to control what happens when a word contains certain characters.

CharAndString: uses RegEx to split the inputed file and then process them into music notes.

FileReader: scans file, passes file text to CharAndString, turns what it recieves into a Phrase

Composition: the front end, manages the GUI.
