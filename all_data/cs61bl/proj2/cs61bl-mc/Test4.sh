#!/bin/bash
#Test that resetting backward appropriately changes the output of log.

java Gitlet init
echo "hello1" > a.txt
echo "hello2" > b.txt
echo "hello2" > c.txt
java Gitlet add a.txt
java Gitlet commit FIRST
java Gitlet rm a.txt
java Gitlet add b.txt
java Gitlet commit 2
java Gitlet add c.txt
java Gitlet commit 3
java Gitlet reset 1
java Gitlet log

echo "Look at the log output above. Check that the only commits are 0 and 1. Check that only the string FIRST is displayed under commit 1. If so, resetting backwardly appropriately changes the output of log."
