def sieve(max_range):
    possible_primes = range(2, max_range) 
    non_primes = []
    
    # mark non-primes
    for i in possible_primes:
        for j in possible_primes:
            # if j equals i don't mark it non-prime
            # if j is a multiple of i then mark it non-prime
            # unless j is already marked as non-prime then skip it
            if j != i and j % i == 0 and j not in non_primes :
                    non_primes.append(j)

    # remove non-primes from possible primes
    for non_prime in non_primes:
        possible_primes.remove(non_prime)
    
    return possible_primes
