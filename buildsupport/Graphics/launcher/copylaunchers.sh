#!/usr/bin/env bash
## Syntax: ./copylaunchers.sh ru russian
## The destination folder where your files will
## be copied to.
dest="../../../app/src/"$2"/res";
src=$1
dpis=(mipmap-mdpi mipmap-hdpi mipmap-xhdpi mipmap-xxhdpi)
## For each dpi do
while IFS= read -r subdir
do
    file="$src"/"$subdir"/ic_launcher.png;
    target=$(basename $file)
    ## We now have everything we need, so lets copy.
    cp --verbose "$file" "$dest"/"$subdir"/"$target";
done < "dpis.list"
