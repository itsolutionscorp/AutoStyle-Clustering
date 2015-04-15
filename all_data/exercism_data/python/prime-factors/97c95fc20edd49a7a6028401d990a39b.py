def prime_factors(natural_num):
    p_factors = []

    while natural_num > 1:
        for i in range(2, int(natural_num + 1)):
            if natural_num % i == 0:
                natural_num /= i
                p_factors.append(i)
                break

    return p_factors
