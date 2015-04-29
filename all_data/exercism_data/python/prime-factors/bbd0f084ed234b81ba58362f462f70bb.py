def prime_factors(n):
    i = 2
    result = []
    while n != 1:
        while n % i == 0:
            result.append(i)
            n /= i
        i+=1
    return result
