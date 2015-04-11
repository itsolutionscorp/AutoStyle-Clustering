from datetime import timedelta

def add_gigasecond(birthdate):
    gigasecond = timedelta(0, 10**9)
    return birthdate + gigasecond
