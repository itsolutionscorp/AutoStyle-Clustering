class DNA(object):
  ADENINE = 'A'
  CYTOSINE = 'C'
  GUANINE = 'G'
  THYMINE = 'T'
  URACIL = 'U'

  map_to_rna = {
      ADENINE: ADENINE,
      CYTOSINE: CYTOSINE,
      GUANINE: GUANINE,
      THYMINE: URACIL
      }

  def __init__(self, strand):
    self.strand = strand

  def to_rna(self):
    return ''.join([DNA.map_to_rna[n] for n in self.strand])
