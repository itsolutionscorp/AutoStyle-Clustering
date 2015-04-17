def slices(series, length):
  slicesL = []
  sliceStart = 0
  sliceEnd   = int(length)
  if len(series) < length or length <= 0:
    raise ValueError
  while sliceEnd <= len(series):
    subslice = []
    for i in series[sliceStart:sliceEnd]:
      subslice.append(int(i))
    slicesL.append(subslice)
    sliceStart += 1
    sliceEnd += 1
  return slicesL
