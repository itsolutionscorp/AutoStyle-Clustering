import datetime

def add_gigasecond(birthdate):
    difference = datetime.timedelta(seconds=10**9)
    return birthdate + difference
