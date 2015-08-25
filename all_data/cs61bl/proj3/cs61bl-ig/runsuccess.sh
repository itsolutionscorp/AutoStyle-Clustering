#!/bin/bash
# usage: runsuccess initfile goalfile type, where there is a solution
#   type is either "hard" or "medium"
echo "Checking a puzzle in 80 seconds"
ulimit -t 80
testdir=./$3
export CLASSPATH=$CLASSPATH:Checker.jar
/bin/rm -f /tmp/out$$
echo $1 " " $2
java -classpath . Solver $testdir/$1 $testdir/$2 > /tmp/out$$
if test "$?" -ne 0
then
    echo "*** Wrong exit status"
fi
java Checker $testdir/$1 $testdir/$2 < /tmp/out$$
if test "$?" -ne 0
then
    echo "*** Incorrect solver output"
fi
/bin/rm -f /tmp/out$$
echo " "
