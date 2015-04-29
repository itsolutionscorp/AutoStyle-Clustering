from datetime import timedelta

def add_gigasecond(date):
    """
    Returns one added gigasecond (1 billion seconds) to given date.
    """
    return date + timedelta(seconds = 10**9)
