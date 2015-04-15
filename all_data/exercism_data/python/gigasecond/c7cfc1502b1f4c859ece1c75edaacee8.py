from datetime import timedelta
'''Given a birthdate, determine the gigasecond birthday in datetime.'''


def add_gigasecond(birthdate):
    secs_per_gigasecond = 10**9
    return birthdate + timedelta(0, secs_per_gigasecond)
