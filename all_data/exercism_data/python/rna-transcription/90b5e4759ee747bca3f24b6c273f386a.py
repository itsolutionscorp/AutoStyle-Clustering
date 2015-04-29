from string import maketrans

class DNA:

  DNA_to_RNA = maketrans('ACGT', 'UGCA');

  def __init__(self, seq=""):
    self.sequence = seq

  def to_rna(self):
    return self.sequence.translate(self.DNA_to_RNA)
