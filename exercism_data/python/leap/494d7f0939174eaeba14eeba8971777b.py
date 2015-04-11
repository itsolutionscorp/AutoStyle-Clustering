def is_leap_year(year):
    return bool((year % 4 == 0) - (year % 100 == 0) + (year % 400 == 0))
