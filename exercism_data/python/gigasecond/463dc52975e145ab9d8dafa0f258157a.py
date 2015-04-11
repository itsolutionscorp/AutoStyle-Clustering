from datetime import timedelta

def add_gigasecond(bday):
    return bday + timedelta(seconds=1e9)
