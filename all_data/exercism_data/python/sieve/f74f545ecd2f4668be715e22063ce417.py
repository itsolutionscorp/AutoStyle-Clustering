import math
def is_prime(n):
    """Returns True if n is a prime number"""
    # only need to iterate up to square root of n, since multiples occur in pairs
    for j in range(3, int(math.sqrt(n)+1), 2):
        if n % j == 0:
            return False
    return True

def sieve(limit):
    """Returns all prime numbers between 2 and limit"""
    # iterate every second number, beginning with 3, to limit to odds
    return [2] + [i for i in range(3, limit+1, 2) if is_prime(i)]
