from datetime import timedelta

GIGA = 10**9

def add_gigasecond(base):
    return base + timedelta(seconds=GIGA)
