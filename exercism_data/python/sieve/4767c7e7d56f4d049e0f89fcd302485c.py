def sieve(until):
    """
    Find all the primes from 2 up to a given number.
    """

    if until < 2:
        raise ValueError("Parameter 'until' must be greater than 1")

    primes = [ ]
    candidates = range(2, until + 1)

    while candidates:
        prime = candidates[0]
        primes.append(prime)
        candidates = [
            candidate for candidate in candidates 
            if candidate % prime != 0 
        ]

    return primes
