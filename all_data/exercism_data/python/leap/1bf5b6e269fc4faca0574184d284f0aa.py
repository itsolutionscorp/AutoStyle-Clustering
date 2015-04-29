def is_leap_year(year):
    if int(year) % 4 == 0:
        return check_hundreds(int(year))
    else:
        return False

def check_hundreds(year):
    if year % 400 == 0:
        return True
    elif year % 100 == 0:
        return False
    else:
        return True
