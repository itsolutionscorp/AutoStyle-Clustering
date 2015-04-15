def is_leap_year(year):
    return can_divide_by_4_not_by_100(year) or can_divide_by_400(year):

def can_divide_by_400(year):
    return year % 400 == 0

def can_divide_by_4_not_by_100(year):
    return year % 4 == 0 and year % 100 != 0
