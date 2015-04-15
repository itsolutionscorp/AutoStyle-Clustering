from string import maketrans

def to_rna(strand):
   """ Takes a DNA strand and translates it to it's RNA complement.  """
   return strand.upper().translate(maketrans('GCTA', 'CGAU'))
