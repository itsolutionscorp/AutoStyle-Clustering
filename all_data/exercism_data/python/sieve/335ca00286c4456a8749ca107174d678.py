def sieve(limit):
    for prime in [x for x in range(2, limit + 1)]:
        for prime_multiple in range(prime*2, limit+1, prime):
            if prime_multiple in test_range:
                test_range.remove(prime_multiple)
    return test_range
