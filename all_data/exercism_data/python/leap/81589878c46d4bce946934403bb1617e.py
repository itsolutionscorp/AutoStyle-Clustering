def is_leap_year(year):
    """ Return True if the given year is a leap year, false otherwise """

    leap = False # Most common case

    # Nested 'if's may be more readable (up to a certain point) than a
    # one-liner:
    # leap = (year % 4 == 0 and year % 100 != 0) or year % 400 == 0
    if year % 4 == 0:
        leap = True
        if year % 100 == 0:
            leap = year % 400 == 0

    return leap
