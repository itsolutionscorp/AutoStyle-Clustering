from datetime import timedelta

GIGASECOND = 10**9

def add_gigasecond(birthdate):
    return birthdate + timedelta(seconds=GIGASECOND)
