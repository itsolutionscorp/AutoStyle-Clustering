def distance(a ,b):
  dist = 0
  for i in range(0, len(a)):
    if a[i] != b[i]:
      dist += 1
  return dist
