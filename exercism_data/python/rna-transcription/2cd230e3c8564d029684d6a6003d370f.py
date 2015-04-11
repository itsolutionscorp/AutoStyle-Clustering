def to_rna(strand):

  strand_map = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
  }

  return ''.join([strand_map[c] for c in strand])

  ''' if you want to import string or are using Python 3.x much simpler
  return dna.translate(string.maketrans(DNA, RNA))
  '''
