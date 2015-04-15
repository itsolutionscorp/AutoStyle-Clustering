def sieve(limit):
    not_prime = set()
    candidates = set(n for n in range(2, limit + 1))
    for candidate in candidates:
        for n in range(candidate*candidate, limit + 1, candidate):
            not_prime.add(n)

    return sorted(candidates - not_prime)
