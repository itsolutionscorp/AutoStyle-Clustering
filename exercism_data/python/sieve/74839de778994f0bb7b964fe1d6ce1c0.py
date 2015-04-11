def sieve(n):
  expected = range(2, n + 1)
  for x in xrange(2, n):
    for j in xrange(2, int(n/x)+1):
      num = x*j
      if num in expected:
        expected.remove(num)
  return expected
