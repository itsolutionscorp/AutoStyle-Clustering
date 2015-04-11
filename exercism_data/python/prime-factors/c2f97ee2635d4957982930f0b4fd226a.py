def prime_factors(what):
    return [f for f in factors(what)]


def factors(what):
    x = 2
    while 1 < what:
        while what % x == 0:
            yield x
            what /= x
        x += 1
