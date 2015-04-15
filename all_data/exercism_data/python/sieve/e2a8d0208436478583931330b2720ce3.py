import array


def sieve(limit):
    sv = array.array("b", [0] * (limit + 1))
    primes = []
    for candidate in range(2, limit):
        if sv[candidate] == 0:
            primes.append(candidate)
            for composite in range(candidate**2, limit, candidate):
                sv[composite] = 1
    return primes
