import datetime

def add_gigasecond(in_date):
    """calculate the date that someone turned
    or will celebrate their 1 Gs anniversary."""
    assert type(in_date) == datetime.date

    # simple addition with the right types :
    return in_date + datetime.timedelta(seconds=10**9)
