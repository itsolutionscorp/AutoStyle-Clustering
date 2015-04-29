from math import sqrt

def sieve(limit):
    record = [True for n in xrange(0, limit + 1)]
    for prime in xrange(2, int(sqrt(limit)) + 1):
        if record[prime]:
            multiplier = prime ** 2
            while multiplier <= limit:
                record[multiplier] = False
                multiplier += prime
        prime += 1

    return [i for i, n in enumerate(record) if n is True][2::]
