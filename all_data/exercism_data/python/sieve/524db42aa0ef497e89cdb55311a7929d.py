def sieve(limit):
    # is_prime[x] represents whether x+2 is prime
    is_prime = [True for x in range(limit - 1)]
    for index, current in enumerate(is_prime):
        if not current:
            continue
        multiples = [(index+2) * (x+1) for x in range (1, limit/(index+2))]
        for multiple in multiples:
            is_prime[multiple-2] = False
    return [index+2 for index, value in enumerate(is_prime) if value]
