def prime_factors(num):
    """
    Function computes prime factors of num
    :param num: number to calculate factors
    :return: (lst) list of prime factors
    """
    lst = []
    factor = 2

    while num > 1 and factor <= num:
        if num % factor == 0:
            num /= factor
            lst.append(factor)
        else:
            factor += 1

    return lst
