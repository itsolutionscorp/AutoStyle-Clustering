def hamming(s1, s2):
  count = 0
  if len(s1) != len(s2):
    count += abs(len(s1) - len(s2))
    if len(s1) > len(s2):
      s1 = s1[:(len(s2) - len(s1))]
    if len(s2) > len(s1):
      s2 = s2[:(len(s1) - len(s2))]
  i = 0
  while i < len(s1):
    if s1[i] != s2[i]:
      count += 1
      i += 1
    else:
      i += 1
  return count
