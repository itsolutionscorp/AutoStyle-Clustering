def is_leap_year(year):
    """Whether the given year is a leap year."""

    if not isinstance(year, int):
        raise ValueError('Argument is not an integer.')
    
    if year < 0:
        raise ValueError('Argument cannot be negative.')

    return (year % 4 == 0 and year % 100 != 0) or year % 400 == 0
