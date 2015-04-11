def sieve(limit):
    # Mapping of numbers to their prime status
    # Start off assuming everything is prime (True)
    table = {i: True for i in range(2, limit+1)}

    i = 2
    while i < limit:
        # Find next unmarked (False) item in table
        while i < limit and not table[i]:
            i += 1

        # Mark all the multiples of i as non-prime, not including i
        table.update(dict.fromkeys(range(2*i, limit+1, i), False))

        # Skip over i because i is prime
        i += 1

    return [i for i, is_prime in table.items() if is_prime]
