### Building our datafiles

The main script is datamunger.R (so you need to install [R](https://cran.r-project.org/bin/macosx/) with packages "downloader", "rsqlite", and "gnumeric"

wordsdb.ods contains the vocabulary and information used in datamunger.R

You'll need [Phatch](http://photobatch.stani.be/download/) to process the svg files into bitmaps

I've been using [Audacity](http://audacityteam.org/download/) to record the audio files, 
and the track files [ru,en,fr,es].txt to label them beforehand. You can use the native_ files
so that the speaker can see the actual words and then the label_ files to write to multiple mp3s.

## build procedure


0. Edit wordsdb.ods and any svg files with any changes you want to make (format should be self-explanatory)
1. Run cleandirectories.sh to start off with an empty workspace
2. $ Rscript datamunger.R
3. $ cd ../Graphics
4. $ Rscript downloader.R
5. In any directories which have changed, run phatch svg2png.phatch
6. $ cd ../Audio/
7. Check if you're happy with all the mp3s, if not, use the *_*.txt files as labels and 
   record, saving the resulting mp3s to words 
8. $ ./copyfiles.sh

...and we're done!
