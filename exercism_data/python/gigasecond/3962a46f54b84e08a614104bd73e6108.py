from datetime import timedelta

def add_gigasecond(testdate):
    return testdate + timedelta(seconds=10**9)
