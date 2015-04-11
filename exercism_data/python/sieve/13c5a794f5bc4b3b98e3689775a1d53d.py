__author__ = 'tracyrohlin'

def isprime(n):
    """check if integer n is a prime"""
    # range starts with 2 and only needs to go up the squareroot of n
    for x in xrange(2, int(math.ceil(n**0.5)+1)):
        if n % x == 0:
            return False
    return True

def sieve(n):
    prime_list = [2]
    for x in range(3, n+1):
        if isprime(x) == True:
            prime_list.append(x)

    return prime_list
