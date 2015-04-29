def sieve(n):

    results = list(range(n))
    results[1] = 0
    for i in results:
        if (i):
            for j in range(2 * i, n, i):
                results[j] = 0

    return [n for n in results if n]
