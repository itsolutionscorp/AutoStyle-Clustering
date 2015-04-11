def distance(a,b):
  dist = 0
  dist = sum(1 for idx,val in enumerate(a) if a[idx] != b[idx])
  return dist
