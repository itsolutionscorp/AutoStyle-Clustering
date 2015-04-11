def prime_factors(number):
    quotient = number
    divisor = 2
    factor_list = []
    while quotient > 1:
        if quotient % divisor == 0:
            factor_list.append(divisor)
            quotient /= divisor
            continue
        divisor += 1
    return factor_list
