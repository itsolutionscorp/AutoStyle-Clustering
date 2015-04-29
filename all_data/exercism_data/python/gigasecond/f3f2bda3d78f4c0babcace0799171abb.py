from datetime import date, timedelta

def add_gigasecond(dob):
    gigasecond = timedelta(seconds = 10**9)
    return dob + gigasecond
