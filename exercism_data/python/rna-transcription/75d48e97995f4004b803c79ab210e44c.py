# Write a program that can translate a given DNA string to the transcribed RNA string corresponding to it.
# The four nucleotides found in DNA are adenine (**A**), cytosine (**C**), guanine (**G**) and thymine (**T**).
# The four nucleotides found in RNA are adenine (**A**), cytosine (**C**), guanine (**G**) and uracil (**U**).
#Given a DNA strand, its transcribed RNA strand is formed by replacing all occurrences of thymine with uracil.

class DNA:
  def __init__(self, nucleotides):
    self.dna = nucleotides
    self.rna = self.dna.replace('T','U').replace('t','u')

  def to_rna(self):
    return self.rna
