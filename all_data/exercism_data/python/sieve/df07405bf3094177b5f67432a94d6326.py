def sieve(n):
    r = range(2,n+1)
    i = 0
    prime = []
    while(i < len(r)):
        prime.append(r[0])
        r = [x for x in r if x % r[i] != 0]
    return prime
