def distance(firstStrand, secondStrand):
  limit = min(len(firstStrand), len(secondStrand))
  distance = 0
  for i in range(limit):
    if firstStrand[i] != secondStrand[i]:
      distance = distance + 1
  return distance + abs(len(firstStrand) - len(secondStrand))
