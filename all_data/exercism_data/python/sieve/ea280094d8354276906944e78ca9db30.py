def sieve(n):
    
    # Create a list of numbers in required range.
    # 1 indicates the number is still a possible prime
    primes = [(number, 1) for number in range(2, n + 1)]

    # Iterate over all numbers
    for i in range(len(primes) - 1):
        
        # If still possible prime, modulo all larger numbers than the current one.
        # Mark with 0 if modulo == 0
        if primes[i][1] == 1:
            for j in range(i + 1, len(primes)):
                if primes[j][0] % primes[i][0] == 0:
                    primes[j] = (primes[j][0], 0);

    # Return numbers still marked with 1
    return([prime[0] for prime in primes if prime[1] == 1])
