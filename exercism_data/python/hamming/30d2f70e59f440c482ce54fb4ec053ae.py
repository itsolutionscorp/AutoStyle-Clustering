def distance(strand1, strand2):
  return len([n for n in xrange(len(strand1)) if strand1[n] != strand2[n]])
