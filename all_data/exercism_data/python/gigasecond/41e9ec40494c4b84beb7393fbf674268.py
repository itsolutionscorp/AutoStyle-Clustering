from datetime import datetime
from datetime import timedelta

def add_gigasecond(dt):
    """
    Calculate, given a birth date, when someone will, or did pass
    their one billion seconds on this planet.
    """
    offset = 1e9
    return dt + timedelta(seconds=offset)
