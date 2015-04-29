import itertools
import math

def erat2():
  D = {}
  yield 2
  for q in itertools.islice(itertools.count(3), 0, None, 2):
    p = D.pop(q, None)
    if p is None:
      D[q*q] = q
      yield q
    else:
      x = p + q
      while x in D or not (x&1):
        x += p
      D[x] = p

def get_primes_erat(n):
  return list(itertools.takewhile(lambda p: p<=n, erat2()))

def prime_factors(n):
  result = []
  for p in get_primes_erat(math.sqrt(n)):
    while (n % p == 0):
      result.append(p)
      n /= p
    if (n < p):
      break
  if (n > 1):
    result.append(n)
  return result
