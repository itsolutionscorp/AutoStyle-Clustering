def hamming(strand0, strand1):
  """Determines the hamming difference between two strands of DNA. The shorter
     strand is length extended by padding on the left
  """


# Left justify both strands to the length of the larger strand
  pad = max(len(strand0), len(strand1))
  strand0 = strand0.ljust(pad)
  strand1 = strand1.ljust(pad)

  if(strand0 == strand1):
    return 0;

  hamming = 0
  i = 0
  while i < len(strand0):
    hamming = hamming + (strand0[i] != strand1[i])
    i = i + 1

  return hamming
