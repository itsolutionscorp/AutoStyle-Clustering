"""
determines if input year is leap year
"""
def is_leap_year(input_year):
    # calculate divisible by
    mod_4 = input_year % 4 == 0
    mod_400 = input_year % 400 == 0
    mod_100 = input_year % 100 == 0

    # apply leap year rules
    if mod_4:
        if not mod_100:
            ret = True
        elif mod_400:
            ret = True
        else:
            ret = False
    else:
        ret = False

    # return is leap year
    return ret
