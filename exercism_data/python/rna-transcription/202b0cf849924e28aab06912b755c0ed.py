def to_rna(bases):
  trans = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
  return ''.join(trans[base] for base in bases)
