def is_leap_year(yr):
    if yr % 400 == 0 or (yr % 4 == 0 and yr % 100 != 0):
        return True
    else:
        return False
