def hamming(s1, s2):
  dist = 0
  for c1, c2 in zip(s1, s2):
    if c1 != c2:
      dist += 1
  dist += abs(len(s1) - len(s2))
  return dist
