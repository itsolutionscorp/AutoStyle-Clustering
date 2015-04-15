class DNA:
  def __init__(self, nucleotide):
    self.nucleotide = nucleotide

  def to_rna(self):
    return self.nucleotide.replace('T', 'U')
