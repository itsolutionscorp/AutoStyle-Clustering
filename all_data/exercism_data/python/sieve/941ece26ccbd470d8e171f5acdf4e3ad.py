def sieve(nums):
    nums = range(2, nums+1)
    primes = []
    primed = True
    for num in nums:
        for prime in primes:
            if num % prime == 0:
                primed = False
                break
        if primed:
            primes.append(num)
        else:
            primed = True
    return primes
