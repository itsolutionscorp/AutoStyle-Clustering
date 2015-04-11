def to_rna(dna):
  complements = {'C': 'G', 'G': 'C', 'A': 'U', 'T': 'A'}
  rna = ""
  for b in dna:
    rna += (complements[b])
  return rna
