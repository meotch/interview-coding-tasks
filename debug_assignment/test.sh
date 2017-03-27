#!/usr/bin/env bash

rm -fr classes
mkdir classes
javac -cp "lib/*" src/com/stanson/anagram/debug/** -d classes

java -cp "lib/*:classes/" com.stanson.anagram.debug.AnagramTest

