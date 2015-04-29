def is_leap_year(year):
    """Check if a given year is a leap year."""

    leapyear = None
    if not year % 4:
        leapyear = True
    if not year % 100:
        leapyear = False
    if not year % 400:
        leapyear = True

    return leapyear
