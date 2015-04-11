def sieve(n):
  numbersL = []
  for i in xrange(n+1):
    if i == 0 or i == 1:
      numbersL.append('neither')
    else:
      numbersL.append(i)

  upperBound = numbersL[-1]

  for i in numbersL:
    if i == 'composite':
      continue
    else:
      n = 2
      while (i * n) <= upperBound:
        numbersL[i * n] = 'composite'
        n += 1

  primesL = []
  for i in numbersL:
    if not i == 'composite' and not i == 'neither':
      primesL.append(i)
  return primesL
