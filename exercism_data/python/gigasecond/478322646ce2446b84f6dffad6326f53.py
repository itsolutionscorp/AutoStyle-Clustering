import datetime

def add_gigasecond(starting_date):
    return (starting_date + datetime.timedelta(0, 10**9))
