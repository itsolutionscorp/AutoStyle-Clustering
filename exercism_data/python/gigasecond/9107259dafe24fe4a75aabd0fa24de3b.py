from datetime import timedelta

# define constants
giga_seconds = timedelta(seconds=10**9)

def add_gigasecond(date):
    return date + giga_seconds
