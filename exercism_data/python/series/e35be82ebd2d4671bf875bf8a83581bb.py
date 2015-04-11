def slices(string, slices):
  if len(string) < slices or slices == 0: raise ValueError

  result = []

  for i in range(len(string)-slices+1):
    result.append([int(c) for c in string[i:i+slices]])

  return result
