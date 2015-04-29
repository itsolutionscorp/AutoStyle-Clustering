def is_leap_year(year):
    return year % 4 == 0 \
        and not hundred(year) \
        or fourhundred(year)

def hundred(year):
    return year % 100 == 0

def fourhundred(year):
    return year % 400 == 0
