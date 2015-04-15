#sieve.py
#Diamonds in the rough


def sieve(limit):
    #take in a limit and returns a list of primes that are less than the limit
    # all primes are 6x plus or minus one
    # for a fast version use a bit array
    numbers = [2, 3] + filter(lambda x: (x % 6 == 1 or x % 6 == 5), range(2, limit))
    for i in numbers:
        numbers = filter(lambda x: x % i != 0 or x == i,  numbers)
    return numbers
