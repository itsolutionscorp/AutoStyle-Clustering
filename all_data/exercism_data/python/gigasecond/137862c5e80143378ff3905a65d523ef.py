from datetime import datetime, timedelta

def add_gigasecond(date):
    """Returns a date one gigsecond from the argument date"""

    return (date + timedelta(seconds = 1000000000))
