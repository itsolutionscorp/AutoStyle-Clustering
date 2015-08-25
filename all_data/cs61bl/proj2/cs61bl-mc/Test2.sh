#!/bin/bash
# Test that a file tnat has been committed at some point  can be restored by checking it out from a commit that tracks that version of the file, even if the file wasn't staged prior to that commit.

java Gitlet init
echo "stuff here in a.txt" > a.txt
java Gitlet add a.txt
java Gitlet commit "can't think of a message"
echo "change contents in a.txt" > a.txt
java Gitlet add a.txt
java Gitlet commit "new commit changing a.txt"
java Gitlet rm a.txt
echo "remove a.txt from tracking"
echo "new file b" > b.txt
java Gitlet add b.txt
java Gitlet commit "commit with b.txt now"
echo "a.txt contents currently in workspace:"
echo "now checkout commit2"
java Gitlet checkout 1 a.txt
cat a.txt

echo "should print out: stuff here in a.txt"