

def is_leap_year(year):
    return (year % 4) == 0 and(not (year % 100) and not (year % 400))
