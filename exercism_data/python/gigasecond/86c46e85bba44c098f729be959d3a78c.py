"""
gigasecond

"""


from datetime import date
from datetime import datetime


def add_gigasecond(date_obj):
    """
    Add a gigasecond to a date object
    and return the new date object

    :param date_obj:
    :return:
    """
    secs = (date_obj - date(1970, 1, 1)).total_seconds() + 10**9
    return datetime.utcfromtimestamp(secs).date()
