class DNA:
  DNA_to_RNA = {
      'A': 'U',
      'G': 'C',
      'C': 'G',
      'T': 'A'
  }

  def __init__(self, seq = ""):
    self.sequence = seq

  def to_rna(self):
    return ''.join(self.DNA_to_RNA[c] for c in self.sequence)
