def slices(digits, n):

  if n < 1 or n > len(digits):
    raise ValueError("The given length does not fit the series")
 
  digits_l = map(int, list(digits))
  slices_l = []

  for i in range(len(digits_l) - n + 1):
    slices_l.append(digits_l[i:i+n]) 
 
  return slices_l 
