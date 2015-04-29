'''exer prime generator'''



def is_prime(num):
    '''return True if num is prime'''
    if num == 2:        # handle special case
        return True
    if num % 2 == 0:    # all other evens are not prime
        return False
    for divisor in range(3, int(num**.5) + 1, 2):  # just check the odd divisors
        if num % divisor == 0:
            return False
    return True

def next_prime():
    '''starting at 2, yield the next prime number'''
    counter = 1
    while True:
        counter += 1
        if is_prime(counter):
            yield counter

def nth_prime(n):
    '''return the nth prime in the series of primes of 2->00'''
    nxtp = next_prime()
    for _ in range(n):
        cur_prime = nxtp.next()
    return cur_prime
