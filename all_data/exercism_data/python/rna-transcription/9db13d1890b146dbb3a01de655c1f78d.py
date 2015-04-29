# -*- coding: utf-8 -*-

def to_rna(dna):
  '''
  As DNA and RNA have 'C' and 'G' in them,
  I first change the 'G' to another letter
  to avoid any conflicts
  '''
  
  rna = dna.replace('G', 'D')
  rna = rna.replace('A', 'U')
  rna = rna.replace('T', 'A')
  rna = rna.replace('C', 'G')
  rna = rna.replace('D', 'C')

  return rna
