'''
sieve.py

Sieve of Eratosthenes
'''

def sieve(limit):
    '''
    Given a limit, return all primes from 2 to the limit
    @param limit: the prime limit
    @returns: list of primes from 2 to the limit
    '''
    primes = range(2, limit+1)
    for v in primes:
        for mult in range(v * 2, limit+1, v):
            try:
                primes.remove(mult)
            except ValueError:
                pass

    return primes
