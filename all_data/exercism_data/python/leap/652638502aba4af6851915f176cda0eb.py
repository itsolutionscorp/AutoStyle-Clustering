def divisible_by(year, n):
    return year % n == 0

def is_leap_year(year):
    
    return (divisible_by(year, 4) and not divisible_by(year, 100)) \
    or divisible_by(year, 400)
    
