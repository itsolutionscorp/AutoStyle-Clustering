from datetime import timedelta
"""
returns the date + 1 gigasecond
"""
def add_gigasecond(date_object):
    gigasecond = timedelta(seconds=10**9)

    return date_object + gigasecond
