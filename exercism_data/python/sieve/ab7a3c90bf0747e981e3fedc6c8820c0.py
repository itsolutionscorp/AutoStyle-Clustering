def sieve(limit):
    # Mapping of numbers to their prime status
    # Start off assuming everything is prime (True)
    # Skip even numbers - there's only one (2) and we add it in specially.
    table = {i: True for i in range(3, limit+1, 2)}
    table[2] = True

    # Start at 3 and iterate through the table 2 at a time
    i = 3
    while i < limit:
        # Find next unmarked (False) item in table
        while i < limit and not table[i]:
            i += 2

        # Mark all the multiples of i as non-prime, not including i
        table.update(dict.fromkeys(range(2*i, limit+1, i), False))

        # Skip over i because i is prime
        i += 2

    return [i for i, is_prime in table.items() if is_prime]
