#!/usr/bin/python
import string

class DNA:
  def __init__(self, sequence):
    self.sequence = sequence

  def to_rna(self):
    return self.sequence.translate(string.maketrans('GCTA', 'CGAU'))
