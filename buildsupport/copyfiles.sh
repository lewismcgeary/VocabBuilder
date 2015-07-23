#!/bin/bash

### our database
mv Data/vocabulary.sqlite ../app/src/main/assets/databases/
###
cp Audio/Words/*.mp3 ../app/src/global/res/raw/
cp Audio/Noises/*.mp3 ../app/src/main/res/raw/
cp Graphics/Flags/bitmap/*.png ../app/src/main/res/drawable/
cp Graphics/pictures/bitmap/*.png ../app/src/main/res/drawable/
cp Graphics/launcher/res/mipmap-xxhdpi/* ../app/src/main/res/mipmap-xxhdpi/
cp Graphics/launcher/res/mipmap-xhdpi/* ../app/src/main/res/mipmap-xhdpi/
cp Graphics/launcher/res/mipmap-mdpi/* ../app/src/main/res/mipmap-mdpi/
cp Graphics/launcher/res/mipmap-hdpi/* ../app/src/main/res/mipmap-hdpi/
cp Graphics/icons/bitmap/* ../app/src/main/res/drawable/
