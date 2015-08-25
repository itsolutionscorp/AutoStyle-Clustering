#!/bin/bash
#NESTED
#Test that a file that has been committed at some point can be restored by checking it out from the commit it was committed at.

java Gitlet init

echo "hello" > a.txt

java Gitlet add a.txt
java Gitlet commit 1
rm a.txt
java Gitlet checkout a.txt

echo "look in working directory to see if folder exists, with a.txt inside. If so, successfully restored."
