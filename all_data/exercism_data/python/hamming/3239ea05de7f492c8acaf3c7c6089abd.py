def distance(dna1, dna2):
  if len(dna1) != len(dna2):
    return 'DNA sequences are not of equal length'
  hamming = 0
  for b1, b2 in zip(list(dna1), list(dna2)):
    if b1 != b2:
      hamming += 1
  return hamming
