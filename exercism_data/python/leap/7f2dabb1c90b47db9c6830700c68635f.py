def is_leap_year(yearn):
    if yearn%400 == 0:
        return True
    elif yearn%100 == 0:
        return False
    elif yearn%4 == 0:
        return True
    else:
        return False
