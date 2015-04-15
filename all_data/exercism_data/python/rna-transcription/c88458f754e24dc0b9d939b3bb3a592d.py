class DNA(object):
  def __init__(self, sequence):
    self._sequence = sequence
  
  def to_rna(self):
    return self._sequence.replace('T','U')
