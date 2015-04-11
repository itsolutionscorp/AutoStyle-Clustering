def hamming(strand0, strand1):
  """Determines the hamming difference between two strands of DNA. The shorter
     strand is length extended by padding on the left
     For performance, experimenting with itertool implementation, as well as
     a generator
  """

  from itertools import izip_longest

  return sum(x != y for x, y in izip_longest(strand0, strand1))
