def divisibleBy(n, d):
    return n % d == 0

def is_leap_year(year):
    """on every year that is evenly divisible by 4
         except every year that is evenly divisible by 100
           unless the year is also evenly divisible by 400"""
    if divisibleBy(year, 100):
        return divisibleBy(year, 400)
    return divisibleBy(year, 4)
