def sieve(limit):
    candidates = [n for n in range(2, limit + 1)]
    for candidate in candidates:
        for n in range(candidate, limit + 1):
            try:
                candidates.remove(candidate * n)
            except ValueError:
                pass

    return candidates
