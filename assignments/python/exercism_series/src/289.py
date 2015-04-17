def slices(seq, n):
  if n <= 0:
    raise ValueError("Overly short slice")
  if n > len(seq):
    raise ValueError("Overly long slice")
  res = []
  for i in range(len(seq)-n+1):
    res.append([int(c) for c in seq[i:i+n]])
  return res
