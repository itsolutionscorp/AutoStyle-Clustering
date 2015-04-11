def sieve(n):
    theList = [True] * n
    theList [0] = theList[1] = False
    prime_list = []
    for (p, prime) in enumerate(theList):
        if prime:
            prime_list.append(p)
            for k in range(p*p, n, p):
                theList[k] = False
    return prime_list
