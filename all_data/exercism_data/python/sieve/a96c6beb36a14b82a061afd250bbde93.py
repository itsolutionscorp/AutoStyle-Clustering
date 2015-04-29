def sieve(limit):
    number_list = [True] * (limit+1)
    number_list[0] = number_list[1] = False

    primes = []
    for (item, isprime) in enumerate(number_list):
        if isprime:
            primes.append(item)
            for x in range(item*item, limit+1,item):
                number_list[x] = False

    return primes
