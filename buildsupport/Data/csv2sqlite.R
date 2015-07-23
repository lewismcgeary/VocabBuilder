## read wordsdb.csv and save as wordlist.sqlite
## wordlist.csv has four columns: IMGFILE, LANG, NAME, and AUDIO
library(sqldf)

# wordlist <- read.csv("wordsdb.csv", header=TRUE)
sqldf("attach 'vocabulary.sqlite' as new")
read.csv.sql("wordsdb.csv", sql = "CREATE TABLE Vocabulary AS SELECT * FROM file",             
             dbname = "vocabulary.sqlite")
read.csv.sql("android_metadata.csv", sql = "CREATE TABLE android_metadata AS SELECT * FROM file",             
             dbname = "vocabulary.sqlite")