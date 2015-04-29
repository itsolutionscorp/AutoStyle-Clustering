def sieve(num):
    # I use num bits to represent num numbers. The position of the bit identifies 
    # the number and a bit-value of 1 stands for prime, 0 for non-prime. 
    numbers = (1 << num) - 1

    for prime in xrange(2, num+1):
        if not numbers & 1<<(prime-1): # not a prime
            continue   
        # mark all multiples of the prime as non-primes
        multiple = prime
        for i in xrange(num/prime): 
            multiple += prime
            numbers = numbers & ~(1<<(multiple-1))
    #build the list of primes by evaluating the bitstring
    return [i for i in xrange(2, num+1) if numbers & 1<<(i-1)]
