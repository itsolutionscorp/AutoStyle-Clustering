def distance(s0, s1):
  return sum([s0[i] != s1[i] for i in xrange(len(s0))])
