def is_leap_year(year):
    if year % 4 == 0:

        if year % 100 == 0:
            if year % 400 == 0:
                return True
            else:
                return False

        # divisible by 4 but not 100
        else:
            return True

    return False
