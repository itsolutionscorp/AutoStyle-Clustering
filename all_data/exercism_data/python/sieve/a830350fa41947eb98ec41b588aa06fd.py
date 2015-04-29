def sieve(n):
    n_list = [ 0 * i + 1 for i in range(0, n - 1) ]
    prime_list = []
    # sieve out the primes
    for a in range(2, n):
        for b in range(2, n):
            if (a * b - 2) < len(n_list):
                if n_list[a * b - 2] == 1:
                    n_list[a * b - 2] = 0
    # translate indices to numbers
    for a in range(0, len(n_list) - 1):
        if n_list[a] == 1:
            prime_list.append(a + 2)
    return prime_list
