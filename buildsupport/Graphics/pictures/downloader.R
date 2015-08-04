#!/usr/bin/r
## downloads all the svg files in column 2 (Location) of pictureurls.csv and names them
## with column 1 (Name)
library(downloader)
library(dplyr)
urls <- read.csv("pictureurls.csv", as.is=T)
urls$Filename <- paste("svg/",urls$BaseName, ".svg", sep = "")
# urls <- urls %>% filter(ImageLocation)
for(i in 1:nrow(urls)) {
  if(!file.exists(urls[i,]$Filename) & !urls[i,2] == ""){
  download(urls[i,]$ImageLocation,urls[i,]$Filename)
  Sys.sleep(3)
  }
}
