def sieve(limit):
    num_list = [num for num in range(2, limit+1)]
    primes_list = []
    while num_list != []:
        prime = num_list[0]
        primes_list.append(prime)
        for num in num_list:
            if num % prime == 0:
                num_list.remove(num)
    return primes_list
        
