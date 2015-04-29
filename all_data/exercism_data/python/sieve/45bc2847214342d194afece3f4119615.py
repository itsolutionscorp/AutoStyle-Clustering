def sieve(prime_range):
    """Runs the Sieve of Sieve of Eratosthenes to find all the primes from 2 up to a given number"""
    retlist = list(range(2,prime_range))
    for i in retlist:
        retlist = list(filter(lambda x: x==i or x%i!=0, retlist))
    return retlist
