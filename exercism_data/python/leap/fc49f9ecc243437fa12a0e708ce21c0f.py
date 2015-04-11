def is_leap_year(year):
    return not year % 4 and year % 100 or not year % 400
