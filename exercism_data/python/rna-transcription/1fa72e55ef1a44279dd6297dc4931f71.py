rna_comp = {'G' : 'C', 'C' : 'G', 'T' : 'A', 'A' : 'U'}

def to_rna(dna):
  '''Return the complement rna strand to the passed dna strand.'''
  return ''.join([rna_comp[n.upper()] for n in dna])
