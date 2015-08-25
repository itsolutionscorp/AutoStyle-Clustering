#!/bin/bash
#Test that you can checkout [id] [file] from an arbitrary commit in the graph (even one in another branch).

java Gitlet init
echo "hello1" > a.txt
echo "hello2" > b.txt
echo "hello2" > c.txt
java Gitlet add a.txt
java Gitlet commit 1
java Gitlet add b.txt
java Gitlet commit 2
java Gitlet branch b
java Gitlet checkout b
java Gitlet rm a.txt
java Gitlet add c.txt
java Gitlet commit 3
rm a.txt
java Gitlet checkout 1 a.txt

echo "look in working directory to see if a.txt exists, if so successfully restored from arbitrary commit in another branch."
