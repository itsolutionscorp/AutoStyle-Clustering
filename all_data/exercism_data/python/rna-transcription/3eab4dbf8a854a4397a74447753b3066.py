class DNA(object):
  THYMINE = 'T'
  
  def __init__(self, sequence):
    self._sequence = sequence
  
  def to_rna(self):
    return self._sequence.replace(DNA.THYMINE, RNA.URACIL)

class RNA(object):
  URACIL = 'U'
