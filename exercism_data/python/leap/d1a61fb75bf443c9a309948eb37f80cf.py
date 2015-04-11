def is_leap_year(year):
    normal_leap = (year % 4 == 0)
    not_leap = (year % 100 == 0)
    not_leap_exception = (year % 400 == 0)

    if normal_leap:
        if not_leap and not not_leap_exception:
            return False
        else:
            return True

