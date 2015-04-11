def distance(a, b):
  tally = 0
  for i in xrange(len(a)):
    if a[i] != b[i]:
      tally = tally + 1

  return tally
