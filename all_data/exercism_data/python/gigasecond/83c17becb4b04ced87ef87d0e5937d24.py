import datetime

def add_gigasecond(birthdate):
    gigasecond = 10**9
    return birthdate + datetime.timedelta(0, gigasecond)
