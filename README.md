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
