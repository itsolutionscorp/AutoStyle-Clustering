def hamming(a, b):
  distance = abs(len(a) - len(b))
  for first, second in zip(a, b):
    if first != second:
      distance += 1
  return distance
