def sieve(limit):
    multiples = []
    primes = []
    for number in range(2, limit+1):
        if number not in multiples:
            primes.append(number)
            for multiples_of_number in range(number*number, limit+1, number):
                multiples.append(multiples_of_number)
    return primes
