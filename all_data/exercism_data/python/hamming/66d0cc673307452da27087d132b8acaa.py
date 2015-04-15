def distance(dna1,dna2):
  count = 0
  dna1 = list(dna1)
  dna2 = list(dna2)
 
  for x, y in zip(dna1,dna2):
    if x != y:
      count = count + 1
 
  return count
