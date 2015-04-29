def is_leap_year(year):

    is_leap_year = False

    if not isinstance(year, int):
        raise ValueError('Argument is not an integer.')
    
    if year < 0:
        raise ValueError('Argument cannot be negative.')

    if year % 4 == 0:

        if year % 100 == 0:

            if year % 400 == 0:
                is_leap_year = True

        else:
            is_leap_year = True

    return is_leap_year
