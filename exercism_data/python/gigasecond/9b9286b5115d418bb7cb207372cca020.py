from datetime import timedelta

def add_gigasecond(the_date):
    gigasecond = timedelta(seconds=10**9)
    new_date = the_date + gigasecond
    return new_date
