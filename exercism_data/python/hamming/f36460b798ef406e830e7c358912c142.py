#!/usr/bin/python

def distance(A, B):
  
  if len(A) != len(B):
    return
  
  diff = 0
  
  A = A.upper()
  B = B.upper()
  
  for i in range(len(A)):
    if A[i] != B[i]:
      diff += 1
      
  return diff
