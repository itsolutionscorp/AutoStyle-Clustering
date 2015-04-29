def divides(num, divisor):
    return num % divisor == 0

def is_leap_year(year):
    return (divides(year, 4) and
            (not divides(year, 100) or divides(year, 400)))
