def is_leap_year(year):
    """
    Returns True if the year passed is a leap-year and False otherwise.
    Non-integer input returns False.
    """

    # Check that year is a number
    if not isinstance(year, int):
        return False

    # Set default result
    result = False

    # Can only be a leap-year if divisible by 4 with exceptions beneath
    if year % 4 == 0:
        result = True

    # Exceptions to the div-by-4 rule
    if year % 100 == 0:
        if year % 400 == 0:
            result = True
        else:
            result = False

    return result
