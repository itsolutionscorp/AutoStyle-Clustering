def sieve(n):
    s = set(range(2,n+1))
    for i in range(2,n+1):
        if not i in s: continue
        
        x = 2
        while i * x <= n:
            if i*x in s:
                s.discard(i*x)
            x += 1
    return list(s)
