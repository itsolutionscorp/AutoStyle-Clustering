def sieve(limit):
    r = list(range(2, limit))
    for i in range(2, limit // 2 + 1):
        for notprime in range(i * 2, limit, i):
            if notprime in r:
                r.remove(notprime)
    return r
