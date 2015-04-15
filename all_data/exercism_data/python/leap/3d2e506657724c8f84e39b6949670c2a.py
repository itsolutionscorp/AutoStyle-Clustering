__author__ = 'agupt15'

def is_leap_year(yr):

    is_div_4 = yr % 4 == 0
    is_div_100 = yr % 100 == 0
    is_div_400 = yr % 400 == 0

    return (is_div_4 and not is_div_100) or (is_div_100 and is_div_400)
