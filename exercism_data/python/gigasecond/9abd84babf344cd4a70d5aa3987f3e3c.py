from datetime import timedelta

def add_gigasecond(birthdate):
    return birthdate + timedelta(seconds=1e9)
