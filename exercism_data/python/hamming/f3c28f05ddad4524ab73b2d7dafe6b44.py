#!/usr/bin/env python
# -*- coding: utf-8 -*-

# Submission file for the python hamming_test exercise.
#
# v1: Initial solution, with user feedback

def distance(strandA, strandB):
  '''
  : Accept 2 DNA strands as strings
  : Return hamming distance of the strings
  '''
  i = 0
  # Check strings are of equal length
  if len(strandA) == len(strandB):

    # loop though strandA and compare to strandB
    for idx, c in enumerate(strandA):

      # If we find a mismatch between the strands increment our count by 1
      if c != strandB[idx]:
        i += 1
      else:
        pass # Nothing to do here

  else:
    # Provide user feedback if the strands are problematic
    print "Strands are not of equal length, cannot calculate hamming distance"

  return i
