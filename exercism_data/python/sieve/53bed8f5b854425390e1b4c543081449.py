def sieve(lim):
    # Python implementation of the sieve of Eratosthenes
    # This is a modified version of the original code available at
    # http://en.wikibooks.org/wiki/Algorithm_Implementation/Mathematics/Prime_number_generation
    PrimeList = []
    n = int(lim)
    markedlist = [False] * (n + 1)
    for i in range(2, n + 1):
        if not markedlist[i]:
            PrimeList.append(i)
            for m in range(i ** 2, n + 1, i):
                markedlist[m] = True
    return PrimeList
