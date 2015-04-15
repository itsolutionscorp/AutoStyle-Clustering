from datetime import timedelta

def add_gigasecond(borndate):
    return borndate + timedelta(seconds=10**9) # Gigasecond = 10**9
