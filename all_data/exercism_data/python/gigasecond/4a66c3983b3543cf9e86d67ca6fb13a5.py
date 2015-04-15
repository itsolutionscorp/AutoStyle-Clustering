#
# captainnurple's code for the Python gigasecond exercise.
#
from datetime import date, timedelta

SECONDS_IN_GIGASECOND = 10**9

def add_gigasecond(birthdate):
    # verify input is valid date
    if type(birthdate) is not date:
        raise TypeError('birthdate input must be a datetime.date, not a %s' % type(arg))

    # Now add gigasecond and return
    return birthdate + timedelta(seconds=SECONDS_IN_GIGASECOND)
