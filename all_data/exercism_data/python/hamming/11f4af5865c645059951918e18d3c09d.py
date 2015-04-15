#!/usr/bin/python2

def distance(first, second):
  counter = 0
  for i in xrange(len(first)):
    if first[i] != second[i]:
      counter += 1
  return counter
