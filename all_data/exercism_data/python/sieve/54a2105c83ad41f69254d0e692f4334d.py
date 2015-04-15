def sieve(number):
    numlist = [i +2 for i in range(number -1)]
    nonprimes = []
    primes = []
    for num in numlist:
        for num2 in numlist:
            if num * num2 <= number:
                nonprimes.append(num * num2)
    nonprimes = set(nonprimes)
    for j in numlist:
        if j not in nonprimes:
            primes.append(j)
    return primes
