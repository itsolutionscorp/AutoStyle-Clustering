def sieve(limit):
    working_range = range(2, limit + 1)
    for n in working_range:
        if n in working_range:
            working_range = [x for x in working_range if x % n or x == n]
    return working_range
