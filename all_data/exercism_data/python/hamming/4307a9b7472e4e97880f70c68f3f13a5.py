#!/usr/bin/python
# File Name: hamming.py
# Created:   22-09-2014

import sys

def hamming(str1, str2):
  """ calculate hamming distance between two nucleotides """
  if len(str1) > len(str2):
    big = len(str1)
  else:
    big = len(str2)

  diff = 0
  for i in xrange(big):
    try:
      if str1[i] != str2[i]:
        diff += 1
    except IndexError:
      diff += 1
  return diff

def main(argv):
  """main func"""

if __name__ == "__main__":
  sys.exit(main(sys.argv))
