def is_leap_year(year):

    divisible_by = lambda i: not year % i
    not_divisible_by = lambda i: year % i

    return divisible_by(4) and not_divisible_by(100) or divisible_by(400)
