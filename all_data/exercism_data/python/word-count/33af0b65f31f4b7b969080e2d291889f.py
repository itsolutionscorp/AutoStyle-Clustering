#!/usr/bin/python
# File Name: wordcount.py
# Created:   22-09-2014

import sys
import string

def word_count(instr):
  """ return dict of words counted """
  rd = {}
  for w in instr.split():
    w = w.lower().translate(None, string.punctuation)
    if not w: continue
    try:
      rd[w] += 1
    except KeyError:
      rd[w] = 1
  return rd

def main(argv):
  """main func"""

if __name__ == "__main__":
  sys.exit(main(sys.argv))
