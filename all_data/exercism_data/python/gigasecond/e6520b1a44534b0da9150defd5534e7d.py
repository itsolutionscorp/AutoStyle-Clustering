import datetime as dt


def add_gigasecond(the_date):
    return the_date + dt.timedelta(days=1000000000.0/60.0/60.0/24.0)
