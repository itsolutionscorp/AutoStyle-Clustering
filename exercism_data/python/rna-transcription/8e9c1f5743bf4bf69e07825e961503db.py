#!/usr/bin/python

def to_rna(dna):
  replacer = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
  return "".join(map(lambda x: replacer[x], dna))
