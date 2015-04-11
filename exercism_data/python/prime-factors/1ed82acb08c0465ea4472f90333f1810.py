def prime_factors(n):
    result = []
    div = 2
    while div <= n:
        while n % div == 0:
            n /= div
            result.append(div)
        div += 1
    return result
