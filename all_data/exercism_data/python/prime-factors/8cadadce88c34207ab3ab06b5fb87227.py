def prime_factors(num):
    factors = []

    while num % 2 == 0:
        factors.append(2)
        num /= 2

    i = 3
    while i <= num and num > 1:
        while num % i == 0:
            factors.append(i)
            num /= i

        i += 2

    return factors

if __name__ == '__main__':
    n = 93819012551
    print prime_factors(n)
    import timeit
    print timeit.timeit(lambda: prime_factors(n), number=1)
