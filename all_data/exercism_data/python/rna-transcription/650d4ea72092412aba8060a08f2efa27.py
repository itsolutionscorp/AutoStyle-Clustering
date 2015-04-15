def to_rna(dna):
  complement = {'A': 'U', 'T': 'A', 'G': 'C', 'C': 'G'}
  return ''.join([complement[base] for base in dna])
