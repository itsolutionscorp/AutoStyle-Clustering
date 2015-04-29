import math

def is_prime(n):
    if n % 2 == 0 and n > 2: 
        return None
    for i in range(3, int(math.sqrt(n)) + 1, 2):
        if n % i == 0:
            return None
    return n

def sieve(rang):
	alcance=range(2,rang+1)
	primos=filter(is_prime, alcance)
	return primos
