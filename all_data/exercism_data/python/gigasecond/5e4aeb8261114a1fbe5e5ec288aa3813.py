# Takes in a date and returns the date that takes place
# a gigasecond (1 billion seconds) later. To do this, we
# import timedelta from datetime and then take the imput
# date plus the timedelta of 1 billion seconds to arrive
# at the answer.

from datetime import timedelta


def add_gigasecond(inputDate):
    """Take in a date and return what date would
       be a gigasecond (1 billion seconds) later.
    """
    return inputDate + timedelta(seconds=10**9)
