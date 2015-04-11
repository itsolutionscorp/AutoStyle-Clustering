#!/usr/bin/python
# File Name: dna.py
# Created:   22-09-2014

import sys

def to_rna(dna):
  """ given dna, turn to rna """
  translation = {
    'G' : 'C',
    'C' : 'G',
    'T' : 'A',
    'A' : 'U'
  }
  return ''.join(translation[n] for n in dna)

def main(argv):
  """main func"""

if __name__ == "__main__":
  sys.exit(main(sys.argv))
