def sieve(num):
    # I use num bits to represent num numbers. The position of the bit 
    # identifies the number and a bit-value of 1 stands for prime, 
    # 0 for non-prime. 
    numbers = (1 << num) - 1

    listOfPrimes = []
    for candidate in xrange(2, num+1):
        if not numbers & 1<<(candidate-1): # not a prime
            continue 
        else:
            listOfPrimes.append(candidate)  
        # mark all multiples of the prime as non-primes
        multiple = candidate
        for i in xrange(num/candidate): 
            multiple += candidate
            numbers = numbers & ~(1<<(multiple-1))
  
    return listOfPrimes
