__author__ = 'banarasitippa'

def sieve(N):
    '''
    program that uses the Sieve of Eratosthenes to find all the primes from 2 up to a given number.

    :param N: int ( answer includes this number if applicable)
    :return:
    '''

    possible_eratosthenes = [True]*(N+1)
    # marking 0 and 1 as non primes
    possible_eratosthenes[0] = False
    possible_eratosthenes[1] = False

    for i in range(2,N+1):
        #- take the next available unmarked number in your list (it is prime) else skip
        if possible_eratosthenes[i]:
            #- mark all the multiples of that number (they are not prime)
            for multiplier in range(2,N//i + 1):
                possible_eratosthenes[i*multiplier] = False


    return [i for i in range(1,N+1) if possible_eratosthenes[i]]
