#sieve of erathostenes
def sieve(number):
    candidates = range(2,number+1)
    primes =[]
    while len(candidates):
        actprime = candidates[0]
        primes.append(actprime)
        for i in range(0, candidates[-1]/actprime+1):
            if i*actprime in candidates:
                candidates.remove(i*actprime)
    return primes
