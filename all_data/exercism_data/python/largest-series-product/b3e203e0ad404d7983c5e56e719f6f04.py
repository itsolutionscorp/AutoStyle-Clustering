import operator

def slices(series, digits):
  if len(series) < digits or digits == 0:
    raise ValueError("Invalid slice size for this series")

  result = []

  for i in range(len(series)-digits+1):
    result.append([int(c) for c in series[i:i+digits]])

  return result

def largest_product(digits, count):
    if count == 0: return 1
    tuples = slices(digits, count)
    return max([reduce(operator.mul, x) for x in tuples])

    #return max([reduce(lambda x, y: x * y, x) for x in tuples])
