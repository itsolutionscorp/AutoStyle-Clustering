ADENINE  = 'A'
CYTOSINE = 'C'
GUANINE  = 'G'
THYMINE  = 'T'
URACIL   = 'U'

class DNA(object):
   def __init__(self, strand):
      self._strand = strand

   def to_rna(self):
      rna = ''
      for nucleotide in self._strand:
         if (nucleotide == ADENINE):
            rna += ADENINE
         elif (nucleotide == CYTOSINE):
            rna += CYTOSINE
         elif (nucleotide == GUANINE):
            rna += GUANINE
         elif (nucleotide == THYMINE):
            rna += URACIL
         else:
            print 'Invalid nucleotide discovered: **%s**' % nucleotide
      return rna
