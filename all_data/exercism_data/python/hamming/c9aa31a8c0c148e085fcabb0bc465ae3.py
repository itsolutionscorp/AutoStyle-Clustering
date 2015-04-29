def hamming(dna_seq_1, dna_seq_2):
 
  hamming_distance = abs(len(dna_seq_1) - len(dna_seq_2))
  
  for a,b in zip(dna_seq_1, dna_seq_2):
    if a != b: 
      hamming_distance += 1

  return hamming_distance
    


