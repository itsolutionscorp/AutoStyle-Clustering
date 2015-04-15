def sieve(self):
    primes = dict()
    primelist = []
    for a in range(2,self):
        primes[a] = True
    for b in range(2,self):
        if primes[b] == True:
            primelist.append(b)
            for c in range(2,int(self / b)+1):
                primes[b*c] = False
    return primelist
