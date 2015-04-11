#!/usr/bin/python

def distance(A, B):
  
  if len(A) != len(B):
    return
  
  compare = zip(A,B)
  
  return sum( 1 if a != b else 0 for (a,b) in compare)
