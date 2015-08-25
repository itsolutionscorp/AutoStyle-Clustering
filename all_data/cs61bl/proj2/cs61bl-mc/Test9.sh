#!/bin/bash
# Test #9; testing Rebase

java Gitlet init

# on master
echo "hello" > hello.txt
java Gitlet add hello.txt
java Gitlet commit 1

java Gitlet branch b

echo "bye?" > bye.txt
java Gitlet add bye.txt
java Gitlet commit 2

echo "hellogoodbye" > bye.txt
java Gitlet add bye.txt
java Gitlet commit 3

# on branch b
java Gitlet checkout b

echo "hello." > hello.txt
java Gitlet add hello.txt
java Gitlet commit 4

echo "hello..." > hello.txt
java Gitlet add hello.txt
java Gitlet commit 5

java Gitlet rebase master

# java Gitlet log

echo "commit 7:"
java Gitlet checkout 7 hello.txt
cat hello.txt
echo "commit 6:"
java Gitlet checkout 6 hello.txt
cat hello.txt
echo "commit 5:"
java Gitlet checkout 5 hello.txt
cat hello.txt
echo "commit 4:"
java Gitlet checkout 4 hello.txt
cat hello.txt
echo "commit 3:"
java Gitlet checkout 3 hello.txt
cat hello.txt
echo "commit 2:"
java Gitlet checkout 2 hello.txt
cat hello.txt
echo "commit 1:"
java Gitlet checkout 1 hello.txt
cat hello.txt

echo "this demonstrates that "
echo "the base branch propagates through the replayed branch"