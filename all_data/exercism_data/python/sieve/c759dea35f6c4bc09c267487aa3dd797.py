"""Not quite the seive method, but something like it"""

def sieve(max_prime):
    primes = [2]
    for i in range(3, max_prime+1):
        if prime(i, primes):
            primes.append(i)
    return primes

def prime(n, primes):
    prime = True
    for m in primes:
        prime = prime and (n % m) != 0
    return prime
