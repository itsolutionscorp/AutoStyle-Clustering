def distance(a,b):
  dist = 0
  for idx,val in enumerate(a):
    if a[idx] != b[idx]:
      dist += 1
  return dist
