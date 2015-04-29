UNKNOWN = 0
PRIME = 1
COMPOSITE = 2

def sieve(until):
    """
    Find all the primes from 2 up to a given number.
    """

    if until < 2:
        raise ValueError("Parameter 'until' must be greater than 1")

    numbers = xrange(2, until + 1)
    flags = dict([ (c, UNKNOWN) for c in numbers ])

    for number in flags:
        if flags[number] == COMPOSITE:
            continue
        flags[number] = PRIME
        for composite in xrange(number * 2, until + 1, number):
            flags[composite] = COMPOSITE

    return [
        number for number in flags 
        if flags[number] == PRIME
    ]
