from functools import partial


def is_divisible(numerator, denominator):
    return numerator % denominator == 0


is_divisible_by_four = partial(is_divisible, denominator=4)
is_divisible_by_one_hundred = partial(is_divisible, denominator=100)
is_divisible_by_four_hundred = partial(is_divisible, denominator=400)


def is_leap_year(year):
    return is_divisible_by_four(year) and not (is_divisible_by_one_hundred(year) ^ is_divisible_by_four_hundred(year))
