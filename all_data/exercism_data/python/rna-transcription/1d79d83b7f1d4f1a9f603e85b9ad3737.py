class DNA:

  def __init__(self, nucleotides):
    self.nucleotides = nucleotides

  def to_rna(self):
    return ''.join(['U' if n == 'T' else n for n in self.nucleotides])
