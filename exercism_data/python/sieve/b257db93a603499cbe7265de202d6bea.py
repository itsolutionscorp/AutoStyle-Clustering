def isPrime(num):
    for i in range(3, int(num ** 0.5) + 1, 2):
        if num % i == 0:
            return False
    return True

def sieve(num_range):
    if (num_range < 2):
        return []

    primes = [2]

    for i in range(3, num_range + 1, 2):
        if (isPrime(i)):
            primes.append(i)

    return primes
