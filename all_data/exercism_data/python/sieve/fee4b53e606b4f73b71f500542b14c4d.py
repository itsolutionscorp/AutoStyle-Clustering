def sieve(upper_limit):
    prime_candidates = range(2, upper_limit + 1)
    primes = []

    while prime_candidates:
        first_unmarked = prime_candidates[0]
        primes.append(first_unmarked)
        prime_candidates = remove_multiples(first_unmarked, prime_candidates)

    return primes


def remove_multiples(first_unmarked, prime_candidates):
    return [number for number in prime_candidates if number % first_unmarked]
