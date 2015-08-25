#!/bin/bash
#Test that merge will generate a .conflicted file if a file has been modified in both branches since the split point.

java Gitlet init
echo "ahello" > a.txt
echo "bhello" > b.txt
echo "chello" > c.txt
echo "dhello" > d.txt

java Gitlet add a.txt
java Gitlet add b.txt
java Gitlet commit 1

java Gitlet branch b

java Gitlet add b.txt
java Gitlet commit 2

java Gitlet checkout b
echo "bhellomodified1" > b.txt
java Gitlet add b.txt
java Gitlet add c.txt
java Gitlet commit 3

java Gitlet checkout master
java Gitlet checkout b.txt
echo "bhellomodified2" > b.txt
Java Gitlet add b.txt
java Gitlet commit 4

java Gitlet checkout b

java Gitlet merge master
java Gitlet branch b

echo "check that the terminal output has 1) printed the message 'Encountered a merge conflict.' 2) printed the message 'Cannot do this command until the merge conflict has been resolved.' If so, 
merge has succesfully detected and handled conflicted files."

