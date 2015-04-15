def is_leap_year(year):
    return divisible(year, 4) and (
        not divisible(year, 100) or divisible(year, 400)
    )

def divisible(dividend, divisor):
    return dividend % divisor == 0
