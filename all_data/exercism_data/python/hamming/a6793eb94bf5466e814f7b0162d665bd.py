def distance(s1, s2):
  if len(s1) != len(s2):
    return None
  ham = 0
  for a,b in zip(s1,s2):
    if a != b:
      ham += 1
  return ham
