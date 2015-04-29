from datetime import date, timedelta

def add_gigasecond(birthdate):
    delta = timedelta(seconds = 10**9)
    return birthdate + delta
