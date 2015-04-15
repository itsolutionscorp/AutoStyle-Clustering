# str(test2).isdecimal()
# C:\Users\matthew.holmes\Desktop\prime-factors>python -c "from prime_factors import prime_factors; prime_factors(60)"


def prime_factors(val_to_test):
    primes = []
    value = val_to_test
    for num in range(2, value+1, 1):
        if value == 1:
            break
        while float(value / num).is_integer():
            primes.append(num)
            value /= num

    return primes
