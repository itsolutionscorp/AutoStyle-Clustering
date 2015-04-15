def distance(strand1, strand2):

  return reduce(lambda acc, pair: acc + 1 if pair[0] != pair[1] else acc, zip(strand1, strand2), 0)
