def to_rna(dna):
  rna = ""
  for c in dna:  rna += {'G':'C', 'C':'G', 'T':'A', 'A':'U'}[c]
  return rna
