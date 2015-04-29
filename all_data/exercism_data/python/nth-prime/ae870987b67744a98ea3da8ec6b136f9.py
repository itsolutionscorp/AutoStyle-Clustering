def nth_prime(n):
    primes = [2]
    curr = 3
    primecount = len(primes)
    
    while primecount < n:
        for prime in primes:
            if curr % prime == 0:
                break
        else:
            primes.append(curr)
        curr += 2
        
        primecount = len(primes)
        
    return primes[-1]
