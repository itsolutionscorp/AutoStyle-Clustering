def sieve(n):
    l = range(n+1)
    def _sieve():
        for i in range(2, n+1):
            if l[i] == 0: continue
            yield i
            for j in range(i+i, n+1, i):
                l[j] = 0
    return list(_sieve())
