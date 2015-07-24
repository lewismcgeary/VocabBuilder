#!/bin/bash
./generatelaunchers.sh ru
./copylaunchers.sh ru russian
./generatelaunchers.sh en
./copylaunchers.sh en english
echo "Uncomment the below lines to enable other languages"
#./generatelaunchers.sh es
#./copylaunchers.sh es spanish
#./generatelaunchers.sh fr
#./copylaunchers.sh fr french
