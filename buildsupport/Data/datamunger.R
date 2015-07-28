### This should do all of the accounting necessary to generate
### our database and audio tag files
library(DBI)
# library(RSQLite)
library(gnumeric)
library(dplyr)
library(tidyr)
library(sqldf)

mastertable <- read.gnumeric.sheet("wordsdb.ods", head=TRUE,sheet.name='Master')
categorytable <- read.gnumeric.sheet("wordsdb.ods", head=TRUE,sheet.name='Categories')
# androidmetadatatable <- read.gnumeric.sheet("wordsdb.ods", head=TRUE,sheet.name='android_metadata')
# First make the data long and write it to the database

dboutput <- mastertable %>% gather(LANG, NAME, EN:RU) %>% select(BaseName, Category, LANG, NAME) %>% arrange(Category,BaseName)
dboutput$IMGFILE <- paste(dboutput$BaseName,".png", sep = "")
dboutput$AUDIO <- paste(dboutput$BaseName,"_",tolower(dboutput$LANG),".mp3", sep = "")

db = dbConnect(RSQLite::SQLite(), dbname="vocabulary.sqlite")
dbSendQuery(conn=db,
            "CREATE TABLE Vocabulary
   (BaseName TEXT,
   Category TEXT,
   LANG TEXT,
   NAME TEXT,
   IMGFILE TEXT,
   AUDIO TEXT,
   PARTOFSPEECH TEXT,
   PRIMARY KEY (BaseName, LANG))
")

dbWriteTable(conn=db, name="Vocabulary", dboutput, append=T, row.names=F)

dbSendQuery(conn=db,
            "CREATE TABLE Categories
   (Category TEXT,
   Image TEXT,
   PRIMARY KEY (Category))")

dbWriteTable(conn=db, name="Categories", categorytable, append=T, row.names=F)

# dbSendQuery(conn=db,
#             "CREATE TABLE android_metadata
#    (locale TEXT,
#    PRIMARY KEY (locale))")
# 
# dbWriteTable(conn=db, name="locale", androidmetadatatable, append=T, row.names=F)

dbDisconnect(db)
read.csv.sql("android_metadata.csv", sql = "CREATE TABLE android_metadata AS SELECT * FROM file",             
            dbname = "vocabulary.sqlite")
closeAllConnections

# Now to write the image download csv file
pix <- mastertable %>% select(BaseName,ImageLocation)
write.csv(pix,"pictureurls.csv", row.names=FALSE, quote=FALSE)

# And to write some files for Audacity labels
masteraudio <- dboutput %>% select(BaseName, LANG, NAME)
masteraudio$Label <- paste(masteraudio$BaseName,"_",tolower(masteraudio$LANG), sep="")
for(lingo in unique(masteraudio$LANG)){
  audio <- masteraudio %>% filter(LANG==lingo)
  Start <- seq(along.with = audio$BaseName, by=2.2)
  Finish <- Start + 1.95
  Label <- audio$Label
  labeltable <- cbind(Start,Finish,Label)
  write.table(labeltable, 
              paste("label_",lingo,".txt", sep=""), 
              sep="\t", 
              row.names=FALSE,
              col.names=FALSE,
              quote=FALSE)
  Native <- audio$NAME
  nativetable <- cbind(Start, Finish, Native)
  write.table(nativetable, 
              paste("native_",lingo,".txt", sep=""), 
              sep="\t", 
              row.names=FALSE,
              col.names=FALSE,
              quote=FALSE)
}
