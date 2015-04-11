def sieve(num):
    numn = num + 1
    not_prime = [False] * numn
    expected = []

    for i in range(2, numn):
        if not_prime[i]:
            continue
        for f in xrange(i * 2, numn, i):
            not_prime[f] = True

        expected.append(i)

    return expected
