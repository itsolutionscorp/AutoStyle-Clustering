def is_leap_year(year):
    # If we can divide by 4. It's a leap year. 
    dividisable_by_4 = year % 4
    # Unless we can divide by 100 too, then it isn't.
    dividisable_by_100 = year % 100
    # UNLESS we can also divide by 400. Then it is.
    dividisable_by_400 = year % 400

    if dividisable_by_4 == 0 and dividisable_by_400 == 0:
        return True
    elif dividisable_by_4 == 0 and dividisable_by_100 == 0:
        return False
    elif dividisable_by_4 == 0 and dividisable_by_100 != 0:
        return True
    else:
        return False
