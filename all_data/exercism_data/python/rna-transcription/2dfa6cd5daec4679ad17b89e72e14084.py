class DNA():
  def __init__(self, seq):
    self.sequence = seq

  def to_rna(self):
    seq = ['U' if n == 'T' else n for n in self.sequence]
    return ''.join(seq)
