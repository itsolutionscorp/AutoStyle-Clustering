def distance(s1, s2):
  d = 0
  for (i, c1) in enumerate(s1):
    c2 = s2[i]
    if c1 != c2:
      d += 1
  return d
