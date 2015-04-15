def to_rna(dna):
  translation = {'A': 'U', 'T': 'A', 'G': 'C', 'C': 'G'}
  return ''.join(map(lambda base: translation[base], dna))
