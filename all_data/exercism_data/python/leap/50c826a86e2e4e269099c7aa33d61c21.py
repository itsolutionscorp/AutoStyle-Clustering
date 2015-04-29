def is_leap_year(inp):
    if inp%4 == 0:
        if inp%100 == 0 and inp%400 != 0:
            return False
        else:
            return True
    else:
        return False
