def is_leap_year(y):
    if y % 4 == 0:
        result = True

        if y % 100 == 0:
            result = False

            if y % 400 == 0:
                result = True

    else:
        result = False

    return result
