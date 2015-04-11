def sieve(end):
  primes = []
  if end < 2:
    return []
  elif 2 == end:
    return [2]
  else:
    for n in range(2,end):
      i = 2
      while i < n:
        if n%i == 0:
          break
          #continue
        else:
          i += 1
      if i == n:
        primes.append(n)
  return primes

print sieve(10)
