class DNA():
  def __init__(self, dna):
    self.dna = dna

  def to_rna(self):
    rna = []
    for char in self.dna:
      if char == 'T':
        rna.append('U')
      else:
        rna.append(char)
    return ''.join(rna)
