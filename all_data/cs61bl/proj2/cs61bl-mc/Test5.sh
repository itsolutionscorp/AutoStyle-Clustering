#!/bin/bash
#Test that log adjusts appropriately when switching from one branch to another.

java Gitlet init
echo "hello1" > a.txt
echo "hello2" > b.txt


echo "bye1" > x.txt
echo "bye2" > y.txt
echo "bye3" > z.txt


java Gitlet add a.txt
java Gitlet commit masterFIRST
java Gitlet add b.txt
java Gitlet commit masterSECOND

java Gitlet branch b
java Gitlet branch c
java Gitlet checkout b

java Gitlet add x.txt
java Gitlet commit bFIRST
java Gitlet add y.txt
java Gitlet commit bSECOND

java Gitlet checkout c
java Gitlet add z.txt
java Gitlet commit cFIRST


java Gitlet log 


echo "Look at the log output above. Check that the only commits are 0, 1, 2, and 45 Check that only the string cFIRST is displayed under commit 5. 
Check masterSECOND is displayed under commit 2. Check that commits 3 and 4 are not printed. If so, log adjusts appropriately when switching from one branch to another."
