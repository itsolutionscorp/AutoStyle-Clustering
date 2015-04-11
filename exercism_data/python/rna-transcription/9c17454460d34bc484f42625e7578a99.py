def to_rna(dna):
  return dna.replace('A', 'U').replace('T', 'A').replace('G', 'X').replace('C', 'G').replace('X', 'C')
