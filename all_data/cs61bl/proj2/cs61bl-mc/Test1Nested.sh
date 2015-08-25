#!/bin/bash
#NESTED
#Test that a file that has been committed at some point can be restored by checking it out from the commit it was committed at.

java Gitlet init
mkdir folder
cd folder
echo "hello" > a.txt
cd ..
java Gitlet add folder/a.txt
java Gitlet commit 1
rm -r folder
java Gitlet checkout folder/a.txt

echo "look in working directory to see if folder exists, with a.txt inside. If so, successfully restored."
