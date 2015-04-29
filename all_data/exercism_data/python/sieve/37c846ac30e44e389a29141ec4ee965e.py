def sieve(limit):
    # Start off assuming everything is prime (True)

    # Start with some "special cases":
    # 0 included for convenience and is not prime
    # 1 is not prime
    # 2 is prime but special case as it's the only even prime
    # Then mark all even numbers as prime - there's only one (2) and we add it in specially.
    table = [False, False, True] + [(i % 2 != 0) for i in xrange(3, limit+1)]

    # Start at 3 and iterate through the table 2 at a time
    limit_sqrt = int(limit**0.5) + 1
    for i in xrange(3, limit_sqrt, 2):
        if table[i]:
            # Found the next prime... mark all its multiples as non-prime (not including itself)
            for j in xrange(i*i, limit+1, i):
                table[j] = False

    return [i for i, is_prime in enumerate(table) if is_prime]
