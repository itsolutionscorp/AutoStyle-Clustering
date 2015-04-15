def slices(digits, n):

  if n < 1 or n > len(digits):
    raise ValueError("The given length does not fit the series")

  digits_l = map(int, list(digits))
  slices_l = []

  for i in range(len(digits_l) - n + 1):
    slices_l.append(digits_l[i:i+n]) 
 
  return slices_l 


def largest_product(digits, n):

  if (len(digits) == 0):
    return 1

  product = 1

  for series in slices(digits, n):
    new_product = reduce(lambda x, y: x * y, series, 1)
    if (new_product > product):
      product = new_product

  return product
