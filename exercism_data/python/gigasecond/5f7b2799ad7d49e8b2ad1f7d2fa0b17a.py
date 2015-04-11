import datetime

def add_gigasecond(birthday):
    """Returns the date of the one gigasecond anniversary of the date
       entered as an argument.

    Args:
        birthday: A birthday as a date object.

    Returns:
        A date object that represents the one gigasecond anniversary
        of the date entered as an argument to the function.
    """
    d = datetime.timedelta(seconds=10**9)
    gig_anniversary = birthday + d
    return gig_anniversary
