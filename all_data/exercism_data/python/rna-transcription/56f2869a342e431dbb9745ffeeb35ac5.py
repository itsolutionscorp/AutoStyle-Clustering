#!/usr/bin/python
# File Name: dna.py
# Created:   22-09-2014

import sys

def to_rna(dna):
  """ given dna, turn to rna """
  rna = ''
  for n in dna:
    if n == 'G': rna += 'C'
    if n == 'C': rna += 'G'
    if n == 'T': rna += 'A'
    if n == 'A': rna += 'U'
  return rna

def main(argv):
  """main func"""

if __name__ == "__main__":
  sys.exit(main(sys.argv))
