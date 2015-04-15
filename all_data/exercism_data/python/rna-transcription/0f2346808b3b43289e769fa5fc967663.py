class DNA():
  def __init__(self, seq):
    self.sequence = seq

  def to_rna(self):
    return self.sequence.replace('T', 'U')
