from datetime import timedelta

def add_gigasecond(birthdate):
    gigasecond = timedelta(seconds=10**9)
    return birthdate + gigasecond
