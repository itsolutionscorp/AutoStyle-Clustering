from __future__ import division
from math import sqrt, ceil
from itertools import compress

# For performance, I want to have access to a range function that always
# delivers a generator-based range in this code.  In Python 2 this is
# xrange(), in Python 3 this has become range().
try:
     irange = xrange
except NameError:
     irange = range

def sieve(until):
    """
    Find all the primes from 2 up to a given number.
    """
    if until < 2:
        raise ValueError("Parameter 'until' must be greater than 1")

    # The 'is_prime' store holds a list of booleans for odd numbers starting
    # with 3. Because the only even prime number is 2, we don't have to
    # consider even numbers for prime-ness and skip those here. When sieving
    # the primes until 10, the store is initialized as [True, True, True, True]
    # (for respectively 3, 5, 7 and 9).

    store_len = until // 2
    is_prime = [True] * store_len

    # Below, all compounds in the store are flagged as is_prime = False.
    # When sieving the primes until 10, the store will end up as
    # [True, True, True, False] (so 3, 5, and 7 are primes).

    for offset, number in enumerate(irange(3, int(sqrt(until)) + 1, 2)):
        if is_prime[offset]:
            first_compound_at = (number*number-3) // 2
            nr_of_compounds = int(ceil((store_len-first_compound_at) / number))
            is_prime[first_compound_at::number] = [False] * nr_of_compounds

    return [2] + list(compress(irange(3, until + 1, 2), is_prime))
