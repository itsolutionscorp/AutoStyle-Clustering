import datetime


def add_gigasecond(dob):
    return dob + datetime.timedelta(seconds=10**9)
