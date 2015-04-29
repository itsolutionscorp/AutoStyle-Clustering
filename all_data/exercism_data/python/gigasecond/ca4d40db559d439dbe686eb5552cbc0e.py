from datetime import timedelta

def add_gigasecond(birth_time):
    return birth_time + timedelta(seconds=(10**9))
