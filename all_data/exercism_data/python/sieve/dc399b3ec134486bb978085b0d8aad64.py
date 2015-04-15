def sieve(N):
  sieve = [True for i in range(N+1)]

  primes = []
  stopFactor = int(N**0.5) + 1
  for i in range(2, stopFactor):
    if sieve[i]:
      j = i*i # all smaller factors of i already eliminated
      while j <= N:
        sieve[j] = False
        j += i
      primes.append(i) # slightly faster than full range comprehension

  return primes + [i for i in range(stopFactor, N+1) if sieve[i]]
