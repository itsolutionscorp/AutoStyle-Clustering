def hamming(dna, rna):
  diff = 0
  return sum(map(lambda d,r: 1 if (d != r) else 0 , dna, rna))
