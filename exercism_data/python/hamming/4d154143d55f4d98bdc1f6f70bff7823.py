def distance(dna_strand1, dna_strand2):
  hamming_distance=0
  for nucleotide1, nucleotide2 in zip(dna_strand1, dna_strand2):
    if nucleotide1!=nucleotide2: hamming_distance+=1
  return hamming_distance
