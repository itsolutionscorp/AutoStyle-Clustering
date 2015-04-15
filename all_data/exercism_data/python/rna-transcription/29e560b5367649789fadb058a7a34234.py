class DNA:
  def __init__(self, dna):
    """ initialize with dna strand """
    self.dna = dna

  def to_rna(self):
    """ replace all uracil with thymidine """
    uracil = 'U'
    thymidine = 'T'
    return self.dna.replace(thymidine, uracil)
