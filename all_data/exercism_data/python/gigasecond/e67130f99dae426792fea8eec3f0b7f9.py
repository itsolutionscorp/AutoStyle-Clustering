#
# Calculates the date one gigasecond away from the given date.
#

from datetime import timedelta

def add_gigasecond(date):
    return date + timedelta(0, 10**9)
