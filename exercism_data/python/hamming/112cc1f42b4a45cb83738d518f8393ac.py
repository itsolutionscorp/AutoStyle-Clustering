def hamming(dna1, dna2):
  dna1_length, dna2_length = len(dna1), len(dna2)
  hamming_distance = abs(dna1_length - dna2_length)
  for idx, c in enumerate(dna1):
    if idx >= dna2_length:
      break
    if c != dna2[idx]:
      hamming_distance += 1

  return hamming_distance
