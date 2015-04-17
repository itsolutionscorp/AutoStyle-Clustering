def slices(numstr, count):
  """ return slices of length count """
  if count > len(numstr) or not count:
    raise ValueError("Slice can't be smaller than input!")
  return [ map(int, list(numstr[i:i+count]))
    for i in xrange(len(numstr)-count+1) ]
