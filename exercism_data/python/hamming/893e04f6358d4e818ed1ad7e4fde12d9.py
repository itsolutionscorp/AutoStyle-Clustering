def distance(strand1, strand2):

  length = len(strand1)

  if length != len(strand2):
    raise(Exception("Strands not of equal length!"))

  return sum([strand1[i] != strand2[i] for i in range(0, length)])
