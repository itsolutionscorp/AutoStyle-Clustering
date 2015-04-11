def distance(left, right):
  if len(left) != len(right):
    raise ValueError("nucleotides must be of equal length")

  distance = 0;
  for i in range(len(left)):
    if left[i] != right[i]:
      distance += 1

  return distance
