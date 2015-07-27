#!/bin/bash
cd $1
rsvg-convert -w 48 -h 48 -o mipmap-mdpi/ic_launcher.png ic_launcher.svg
rsvg-convert -w 72 -h 72 -o mipmap-hdpi/ic_launcher.png ic_launcher.svg
rsvg-convert -w 96 -h 96 -o mipmap-xhdpi/ic_launcher.png ic_launcher.svg
rsvg-convert -w 144 -h 144 -o mipmap-xxhdpi/ic_launcher.png ic_launcher.svg
rsvg-convert -w 512 -h 512 -o hiresicon/hiresicon.png ic_launcher.svg
cd ..

