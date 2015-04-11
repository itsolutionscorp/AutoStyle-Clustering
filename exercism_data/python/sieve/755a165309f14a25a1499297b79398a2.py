#sieve.py
#Diamonds in the rough

#take in a limit and returns a list of primes that are less than the limit
def sieve(limit):
    numbers = range(2, limit)
    for i in range(2, limit/2):
        numbers = filter(lambda x: x % i != 0 or x == i,  numbers)
    return numbers
