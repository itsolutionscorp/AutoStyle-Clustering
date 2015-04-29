def to_rna(dna):
  table = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
  return ''.join([table[x] for x in dna])
