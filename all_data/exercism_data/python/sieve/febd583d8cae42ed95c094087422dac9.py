def sieve(n):
    non_primes = []
    for num in range(2, n+1):
        non_primes.extend(range(num**2, n+1, num))
    return [i for i in range(2, n+1) if i not in non_primes] 
