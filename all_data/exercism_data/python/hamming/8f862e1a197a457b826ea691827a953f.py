def distance(dna1, dna2):
  num = min(len(dna1), len(dna2))
  count = 0
  for num in range(0, num):
    if list(dna1)[num] != list(dna2)[num]:
      count += 1

  return count
