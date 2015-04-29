#!/usr/bin/python

def to_rna(dna_strand):
  rna_strand = ""

  # Iterate through all the nucleotides in the dna strand form the rna strand
  # by replacing each with its rna complement
  for nucleotide in dna_strand:
    if nucleotide == 'G':
      rna_strand += 'C'
    elif nucleotide == 'C':
      rna_strand += 'G'
    elif nucleotide == 'T':
      rna_strand += 'A'
    elif nucleotide == 'A':
      rna_strand += 'U'

  return rna_strand
