def slices(digits, n):
  
  m = len(digits)

  if n > m or n == 0:
    raise(ValueError("n cannot be larger than the length of the digits provided."))

  if n == m:
    return [[int(_d) for _d in digits]]

  series = []
  for i in range(0, m - n + 1):
    series.append([int(_x) for _x in list(digits[i:(i + n)])])

  return series
