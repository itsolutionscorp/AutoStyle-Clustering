def sieve(limit):
    L = list(range(2,limit+1))
    n = 0
    while n <= len(L):

        for i in L[n:]:
            if i != L[n] and i % L[n] == 0:
                L.remove(i)
        n += 1
    return L
