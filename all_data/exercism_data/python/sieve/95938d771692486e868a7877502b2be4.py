# Sieve Python exercism, 1st iteration

def sieve(n):
    primes = []
    ran = range(2,n+1)
    prime = 2
    while prime != 0:
        primes.append(prime)
        ran = [m for m in ran if m % prime != 0]
        if ran != []:
            prime = ran[0]
        else:
            prime = 0
   
    return primes
