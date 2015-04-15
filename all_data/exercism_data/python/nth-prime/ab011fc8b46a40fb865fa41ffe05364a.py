from random import randint
primes = [2, 3]

def isprime(n):
    prime = False
    
    for i in range(3):
        b = randint(1, n-1)**(n-1)
        if b%n == 1:
            prime = True
    return prime

def nth_prime(n):
    test = primes[-1]
    while n>len(primes):
        test+=2
        if isprime(test):
            primes.append(test)
    
    return primes[n-1]
