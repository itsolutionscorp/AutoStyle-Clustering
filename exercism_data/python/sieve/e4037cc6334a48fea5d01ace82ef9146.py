"""
sieve.py contains one function, sieve.  Sieve uses the Sieve of Eratosthenes
method of finding primes less than a given number. It starts by creating a
list of all natural numbers between 2 and n.  Then it removes all multiples
of each number less than or eaqual to n that haven't already been removed
starting with 2 and continuing until the list is only primes. Sieve then
returns the list of primes.
"""
def sieve(n):
    
    primes = []
    
    for x in xrange(2, n + 1):
        primes.append(x)
    
    for p in primes:
        i = 2
        while p*i <= n:
            try:
                primes.remove(p*i)
            except ValueError:
                pass
            i += 1

    return primes
