def DNA(nucleotid):
  return Dna(nucleotid)

class Dna:
  def __init__(self, nucleotid):
    self.nucleotid = nucleotid
    self.mapping = {'A': 'A', 'C': 'C', 'G': 'G', 'T': 'U'}

  def to_rna(self):
    l = [self.mapping[char] for char in self.nucleotid]
    return ''.join(l)
