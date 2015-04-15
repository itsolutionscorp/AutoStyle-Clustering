def sieve(n):
    seq = range(2, n+1)
    for i in seq:
        for j in seq:
            if j != i and j % i == 0:
                seq.remove(j)
    return seq
