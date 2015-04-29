def is_leap_year(year):
    not_century = year % 400 == 0 or not year % 100 == 0
    return year % 4 == 0 and not_century
