ADENINE  = 'A'
CYTOSINE = 'C'
GUANINE  = 'G'
THYMINE  = 'T'
URACIL   = 'U'

DNA_to_RNA = {
      ADENINE  : ADENINE ,
      CYTOSINE : CYTOSINE,
      GUANINE  : GUANINE ,
      THYMINE  : URACIL  }

class DNA(object):
   def __init__(self, strand):
      self._strand = strand

   def to_rna(self):
      rna_nucleotides = [DNA_to_RNA[n] for n in self._strand]
      return ''.join(rna_nucleotides)
