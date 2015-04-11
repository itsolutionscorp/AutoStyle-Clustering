from datetime import timedelta

def add_gigasecond(date):
    """Add a gigasecond to a date object"""
    return date + timedelta(seconds=10**9)
