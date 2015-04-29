import datetime


def add_gigasecond(dob):
    return dob + datetime.timedelta(0, 10**9)
