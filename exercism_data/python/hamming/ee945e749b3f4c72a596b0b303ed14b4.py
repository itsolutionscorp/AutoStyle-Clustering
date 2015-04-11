def distance(firstStrand, secondStrand):
  distance = 0
  for pair in zip(list(firstStrand), list(secondStrand)):
  	if pair[0] != pair[1]:
  		distance = distance + 1
  return distance + abs(len(firstStrand) - len(secondStrand))
