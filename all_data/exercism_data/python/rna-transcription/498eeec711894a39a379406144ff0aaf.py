__author__ = 'jakesawyer'

from string import replace

def to_rna(dna):
  rna = ""
  for i in dna:
    if i == 'G':
      rna = rna + 'C'
    if i == 'C':
      rna = rna + 'G'
    if i == 'T':
      rna = rna + 'A'
    if i == 'A':
      rna = rna + 'U'
  return rna
