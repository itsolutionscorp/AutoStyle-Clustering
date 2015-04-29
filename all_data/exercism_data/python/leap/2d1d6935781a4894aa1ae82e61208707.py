def is_leap_year(year):
#Every Year that is evenly divisible by 4
#Unless its evenly divisibly by 100
#Unless evenly divisibly by 400
    return year % 4 == 0 and year % 100 != 0 or year % 400 == 0
