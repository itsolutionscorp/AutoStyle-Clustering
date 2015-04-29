def is_leap_year(year):
    """Check if a given year is a leap year."""
   
    if not year % 400:
        return True
    elif not year % 100:
        return False
    elif not year % 4:
        return True

    return False
