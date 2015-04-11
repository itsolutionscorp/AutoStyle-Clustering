from datetime import timedelta
def add_gigasecond(start_date):
    giga = 10**9
    increment = timedelta(seconds=giga)
    anniversary = start_date + increment
    return anniversary
