import string

class DNA:
  def __init__(self, dna):
    self.dna = dna

  def to_rna(self):
    return string.replace(self.dna, 'T', 'U') 
