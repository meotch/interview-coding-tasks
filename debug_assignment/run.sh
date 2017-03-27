#!/usr/bin/env bash
echo $1 $2

rm -fr classes
mkdir classes
javac -cp "lib/*" src/com/stanson/anagram/debug/** -d classes

java -cp "lib/*:classes/" com.stanson.anagram.debug.Anagram "$1" "$2"

