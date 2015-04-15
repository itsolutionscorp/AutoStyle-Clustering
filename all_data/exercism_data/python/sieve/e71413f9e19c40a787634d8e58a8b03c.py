def sieve(n):
    x = range(2, n+1)
    y = range(2, n+1)
    for i in x:
        for j in y:
            if i*j > n+1:
                continue
            elif i*j in y:
                y.remove(i*j)
    return y

"""this seems to be a VERY bad algorithm"""
