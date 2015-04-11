def prime_factors(num):
    results = []

    divisor = 2
    while num != 1 and num >= divisor:
        if num % divisor == 0:
            results.append(divisor)
            num = num / divisor
        else:
            divisor += 1

    return results
        
        
