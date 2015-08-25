#!/bin/bash
# Test #8; testing Rebase

java Gitlet init

# on master
echo "hello" > hello.txt
java Gitlet add hello.txt
java Gitlet commit 1

java Gitlet branch b

echo "hello." > hello.txt
java Gitlet add hello.txt
java Gitlet commit 2

echo "hello..." > hello.txt
java Gitlet add hello.txt
java Gitlet commit 3

# on branch b
java Gitlet checkout b

echo "hello!" > hello.txt
java Gitlet add hello.txt
java Gitlet commit 4

echo "hello!!!" > hello.txt
java Gitlet add hello.txt
java Gitlet commit 5

java Gitlet rebase master

java Gitlet log

echo "The log output correctly indicates"
echo "the replayed files (commits 6 and 7 "
echo "replayed from 2 and 3, respectively)."

