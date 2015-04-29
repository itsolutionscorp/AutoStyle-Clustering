def distance(strand_one, strand_two):
  strands = zip( list(strand_one), list(strand_two) )
  distance = sum( [ 1 for pair in strands if pair[0] != pair[1] ] )
  return distance
