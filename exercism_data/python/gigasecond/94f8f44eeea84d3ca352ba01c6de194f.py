from datetime import timedelta

GIGASECOND = timedelta(seconds=1e9)


def add_gigasecond(givendate):
    """
    Calculates the date that is 1 Gs (gigaseconds) after the given date.
    """
    return givendate+GIGASECOND
