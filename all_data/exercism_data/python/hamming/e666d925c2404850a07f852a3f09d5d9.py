def distance(strand1, strand2):
  r = 0
  for s1, s2 in zip(strand1, strand2):
    if s1 != s2:
      r = r + 1
  return r
