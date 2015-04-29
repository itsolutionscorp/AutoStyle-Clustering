#!/usr/bin/python
import string

class DNA:
  def __init__(self, sequence):
    self.map = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    self.sequence = sequence

  def to_rna(self):
    return self.sequence.translate(string.maketrans('GCTA', 'CGAU'))
    # if self.sequence    == 'G':
    #   return 'C'

    # elif self.sequence  == 'C':
    #   return 'G'

    # elif self.sequence  == 'T':
    #   return 'A'

    # elif self.sequence  == 'A':
    #   return 'U'
