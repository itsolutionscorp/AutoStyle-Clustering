def slices(text, size):
  if size > len(text) or size == 0:
    raise ValueError
  return [[int(item) for item in text[index:index+size]] for index in xrange((len(text) - size) + 1)]
