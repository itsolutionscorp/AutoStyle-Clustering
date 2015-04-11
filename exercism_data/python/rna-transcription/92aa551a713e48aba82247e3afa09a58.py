#!/usr/bin/env python

from string import maketrans

rna_trans = maketrans("GCTA","CGAU")

def to_rna(dna):
  return dna.translate(rna_trans)
