def prime_factors(val_to_test):
    primes = []
    value = val_to_test
    for num in range(2, value+1, 1):
        while float(value / num).is_integer():
            primes.append(num)
            value /= num
        
        if value == 1:
            break

    return primes
