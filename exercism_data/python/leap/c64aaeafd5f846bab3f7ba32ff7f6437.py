def is_leap_year(data):
    if data % 100 == 0 and data % 400 == 0:
        return True

    if data % 100 == 0:
        return False

    if data % 4 == 0:
        return True
