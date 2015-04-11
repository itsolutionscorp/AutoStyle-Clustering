def sieve(n):
    multiples = set()
    sieved = []

    for i in range(2, n + 1):
        if i not in multiples:
            sieved.append(i)
            multiples.update(range(i * i, n + 1, i))

    return sieved
