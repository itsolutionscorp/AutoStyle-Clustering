def is_leap_year(year):
    return bool( ( year % 4 == 0 and year % 100 != 0 ) or year % 400 == 0 )
