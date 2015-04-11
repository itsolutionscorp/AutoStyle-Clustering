def sieve(n):
    r = range(2,n+1)
    i = 0
    while(i < len(r)):
        r = [x for x in r if x % r[i] != 0 or r[i] == x]
        i += 1
    return r

print sieve(30)
