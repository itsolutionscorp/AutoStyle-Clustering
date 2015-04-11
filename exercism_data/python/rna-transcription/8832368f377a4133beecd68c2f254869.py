rna = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}

def to_rna(dna):
  return ''.join(rna[c] for c in dna)
