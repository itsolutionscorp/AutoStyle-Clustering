def distance(strand1, strand2):
  r = 0
  for s1, s2 in zip(list(strand1), list(strand2)):
    if s1 != s2:
      r = r + 1
  return r
