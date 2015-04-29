from datetime import timedelta

def add_gigasecond(dateTimeObj):
    gigasecond=10**9
    return dateTimeObj + timedelta(0,gigasecond)
