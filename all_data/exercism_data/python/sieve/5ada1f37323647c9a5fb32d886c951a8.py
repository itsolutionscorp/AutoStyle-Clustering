def sieve(num):
    list_of_ints = list(range(2, num+1))
    not_primes = []

    for p in list_of_ints:
        two_p = p*2
        for not_prime in list(range(two_p, num+1, p)):
            not_primes.append(not_prime)

    primes = list(set(list_of_ints) - set(not_primes))
    primes.sort()
    return primes
