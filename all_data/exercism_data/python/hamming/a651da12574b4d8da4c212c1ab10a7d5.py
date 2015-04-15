def distance(seq1, seq2):
  res = 0
  for i in range(len(seq1)):
    if seq1[i] != seq2[i]:
      res += 1
  return res
