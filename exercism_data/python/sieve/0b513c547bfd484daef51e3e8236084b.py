def sieve(limit):
    number_list = range(2, limit + 1)
    primes = []
    while number_list:
        prime = number_list.pop(0)
        primes.append(prime)
        number_list = [n for n in number_list if n % prime != 0]
    return primes
