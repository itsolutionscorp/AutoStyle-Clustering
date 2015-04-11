def is_leap_year(yyyy):
    if (yyyy % 4 == 0 and
            yyyy % 100 == 0 and
            yyyy % 400 == 0):
        return True
    elif (yyyy % 4 == 0 and
            yyyy % 100 == 0 and
            yyyy % 400 is not 0):
        return False
    elif (yyyy % 4 == 0):
        return True

    return False
