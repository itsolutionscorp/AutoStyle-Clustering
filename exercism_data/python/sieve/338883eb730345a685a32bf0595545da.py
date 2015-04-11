def sieve(limit):
    sieve_list = [0, 0] + [1] * (limit-1)
    for i, is_prime in enumerate(sieve_list):
        if is_prime:
            for j in range(2 * i, limit + 1, i):
                sieve_list[j] = 0
    return [i for i, is_prime in enumerate(sieve_list) if is_prime]
