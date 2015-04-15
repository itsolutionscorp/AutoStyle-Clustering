def is_leap_year(year):
    return (
        is_dividable(year,400) or
        (is_dividable(year,4) and not is_dividable(year,100))
    )

def is_dividable(dividend, divisor):
    return dividend % divisor == 0
