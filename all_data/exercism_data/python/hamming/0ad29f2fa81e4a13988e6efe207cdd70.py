def distance(dna1, dna2):

  return sum([ i != j for i, j in zip(dna1, dna2)])
