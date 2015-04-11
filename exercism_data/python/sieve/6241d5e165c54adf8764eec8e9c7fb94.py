def sieve(N):
  primes=[]
  i=1
  while i<N:
    i+=1
    prime=True
    for p in primes:
      if i%p==0:
        prime=False
        break
    if prime:
      primes.append(i)
  return primes
