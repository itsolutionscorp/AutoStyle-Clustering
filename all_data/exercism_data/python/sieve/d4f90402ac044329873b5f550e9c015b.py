def sieve(top_bound):
    if top_bound < 2:
        raise ValueError('list is empty')
    primes = [i for i in range(2, top_bound)]
    counter = 0
    while primes[counter] * 2 <= top_bound:
        for n in range(2,int(top_bound/primes[counter])+1):
            if (primes[counter] * n) in primes:
                primes.remove(primes[counter]*n)
        counter += 1
    return primes
