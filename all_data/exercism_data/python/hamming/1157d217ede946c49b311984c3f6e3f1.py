def distance(strandA, strandB):
  length = min(len(strandA), len(strandB))
  distance = 0
  for i in range(0, length):
    if strandA[i] != strandB[i]:
      distance += 1
  return distance
