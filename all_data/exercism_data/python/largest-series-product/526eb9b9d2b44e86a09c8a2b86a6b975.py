from operator import mul

def largest_product(seq, length):
  l = []
  for s in slices(seq, length):
    l.append(reduce(mul, s, 1))
  return max(l)

def slices(seq, n):
  if n < 0:
    raise ValueError("Overly short slice")
  if n > len(seq):
    raise ValueError("Overly long slice")

  res = []
  for i in range(len(seq)-n+1):
    res.append([int(c) for c in seq[i:i+n]])
  return res
