def distance(dna1, dna2):
  dist = 0
  for n1, n2 in zip(dna1, dna2):
    if n1 != n2:
      dist += 1
  return dist
