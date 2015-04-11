def sieve(max_number):
    possible_primes = range(max_number + 1)
    is_prime = [False, False] + [True] * (max_number - 1)
    for number in range(int(round(max_number ** 0.5)) + 1):
        if is_prime[number]:
            is_prime[number ** 2 : max_number + 1 : number] = [False] * ((max_number - number ** 2) / number + 1)
    primes = [i for i in possible_primes if is_prime[i]]
    return primes
