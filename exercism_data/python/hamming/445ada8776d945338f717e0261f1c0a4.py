def hamming(a, b):
  count = min( len(a), len(b) )
  mag =   max( len(a), len(b) ) - count

  for i in range(0, count):
    if a[i] != b[i]: mag += 1

  return mag
