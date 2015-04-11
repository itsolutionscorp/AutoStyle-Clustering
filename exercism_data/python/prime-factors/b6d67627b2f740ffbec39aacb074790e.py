def prime_factors(n):
    result = []
    odd = 3

    while n % 2 == 0:
        result.append(2)
        n /= 2

    while n > 1:
        if odd**2 > n:
            result.append(n)
            break

        while n % odd == 0:
            result.append(odd)
            n /= odd

        odd += 2

    return result
