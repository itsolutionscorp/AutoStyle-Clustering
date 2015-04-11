class DNA:
  def __init__(self, seq = None):
    self.sequence = seq
    # I guess this is inefficient as it declares the mapping every time a new object is created?
    self.DNA_to_RNA = {
      'A': 'U',
      'G': 'C',
      'C': 'G',
      'T': 'A'
    }

  def to_rna(self):
    return ''.join(self.DNA_to_RNA[c] for c in self.sequence)
