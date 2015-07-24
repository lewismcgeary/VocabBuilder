for i in svg/*.svg; do rsvg-convert $i -w 480 -a -f svg -o `echo $i | sed -e 's/svg$/new.svg/'`; done
