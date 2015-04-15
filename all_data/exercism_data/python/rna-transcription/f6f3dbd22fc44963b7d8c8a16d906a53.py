class DNA:

  mapping = {"G": "C", "C": "G", "T": "A", "A": "U"}

  def __init__(self, nucleotides):
    self.nucleotides = nucleotides

  def to_rna(self):
    table = string.maketrans("".join(self.mapping.keys()), "".join(self.mapping.values()))
    return self.nucleotides.translate(table)
