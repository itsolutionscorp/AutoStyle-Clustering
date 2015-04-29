class DNA:
  def __init__(self, dna):
    """ initialize with dna strand """
    self.dna = dna

  def to_rna(self):
    """ replace all thymidine with uracil """
    thymidine = 'T'
    uracil = 'U'
    return self.dna.replace(thymidine, uracil)
