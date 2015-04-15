def slices(series,length):
  if length > len(series) or length < 1:
    raise ValueError

  return [map(int,series[i:i+length]) for i in xrange(len(series)-length+1)]
