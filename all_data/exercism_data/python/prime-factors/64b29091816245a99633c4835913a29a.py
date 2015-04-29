def prime_factors(num):
    result = []
    while num % 2 == 0:
        result.append(2)
        num /= 2
    i = 3
    while i * i <= num:
        while num % i == 0:
            result.append(i)
            num /= i
        i += 2
    if num > 1:
        result.append(num)
    return result
