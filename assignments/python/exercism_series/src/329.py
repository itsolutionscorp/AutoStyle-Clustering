def slices(s, n):
  if n > len(s) or n < 1:
    raise ValueError('cannot have n > len(s) or n < 1')
  s = [int(x) for x in s]
  num_slices = len(s)-n+1
  return [s[i:i+n] for i in range(num_slices)]
