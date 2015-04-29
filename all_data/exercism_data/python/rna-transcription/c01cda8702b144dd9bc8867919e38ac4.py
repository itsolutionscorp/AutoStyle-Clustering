import string

class DNA:
  
  DNA_to_RNA = string.maketrans('ACGT', 'UGCA');
  
  def __init__(self, seq=""):
    self.sequence = seq

  def to_rna(self):
    return self.sequence.translate(self.DNA_to_RNA)
