#!/bin/bash
#Test that merge will commit with files from the other branch if those files had been modified in the other branch but not in the current branch since the split point.

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
java Gitlet add c.txt
java Gitlet commit 2

java Gitlet checkout b
echo "bhellomodified1" > b.txt
java Gitlet add b.txt
java Gitlet add c.txt
java Gitlet commit 3




java Gitlet checkout master

java Gitlet merge b


echo "check that the terminal output has no conflicted file message. Check that Commit 4 has been created and that it contains a b.txt file who's string is 'behellomodified1'. If so,
 merge successfully commits with files from the other branch if those files had been modified in the other branch but not in the current branch since the split point."

