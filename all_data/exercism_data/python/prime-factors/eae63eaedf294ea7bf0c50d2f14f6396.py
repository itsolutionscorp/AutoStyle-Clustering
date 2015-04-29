def prime_factors(n, result=[]):
    if n == 1:
        temp = result.copy()
        result.clear()
        return temp
    divisor = None
    for divisor in range(2, n+1):
        if n % divisor == 0:
            n = n // divisor
            result.append(divisor)
            break
    return prime_factors(n, result)
