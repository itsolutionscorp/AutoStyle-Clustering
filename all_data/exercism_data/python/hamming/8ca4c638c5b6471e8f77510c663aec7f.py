def distance(seq1, seq2):
  res = 0
  for a, b in zip(seq1,seq2):
    if a != b:
      res += 1
  return res
