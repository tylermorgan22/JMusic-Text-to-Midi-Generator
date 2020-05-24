# JMusic-Text-to-Midi-Generator
This program lets the user choose a text File and set parameters for the generation of 3 new Midi file based off of the text File.

SETUP:
1. Follow these instructions from JMusic:
https://explodingart.com/jmusic/GetjMusic.html
2. Ensure that the JMusic files are in your external Archives and the Inst files are in your build path
3. Put these files into the src folder

OPERATION:
1. Run the project
2. Press "CHOOSE FILE"
3. Choose a text file
4. Choose a key and a scale in the bottom where it says "C" and "AEOLEAN_SCALE"
5. In each of the three ACTION tabs, choose something that you want to happen to the melody when the scanner hits a word with the characters represented by check boxes
6. Use the checkboxes to select trigger characters for the specified actions
7. Press start. The output files will be rendered to Drums1.mid, Melody1.mid, and Bass1.mid

WHAT I DO WITH THE OUTPUT FILES:
In REAPER, a DAW, I set up 3 new tracks:
  1. Sampler: Choose the sound for the melody. Add a Reverb effect. Import Melody1.mid.
  2. Sampler: Choose a bass sound, add a bass amp simulator if the effect is not already amplified or "electronic" sounding.
    Add a Reverb effect. Import Bass1.mid.
  3. Drum Machine: I use this - https://www.dskmusic.com/dsk-drumz-akoustik/
  CharAndString.java is set to make notes in the pitch range of this sampler, C2-B2. I also add a Reverb effect to this plugin. Import       Drums1.mid
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
    I did a lot of minor testing along the way. I used a text file with the string "ah bh ch dh eh fh gh hai" to test all of the Actions and the melody writer. I used my notes from AP European history as another testing file. I used this file to print into the console all of the strings that I scanned to make sure the RegExes that I used worked. For an example of what the program can produce, I have submitted what I made with the first section of the Bee Movie.
