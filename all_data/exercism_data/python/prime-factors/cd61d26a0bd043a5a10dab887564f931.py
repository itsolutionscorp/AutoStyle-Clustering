def prime_factors(n):
    factor = []
    odd = 3

    while not n % 2:
        factor.append(2)
        n /= 2

    while n > 1:
        while n % odd == 0:
            factor.append(odd)
            n /= odd

        odd += 2

        if odd^2 > n:
            if n > 1:
                factor.append(n)
            break

    return factor
