from datetime import timedelta
GIGA_SECOND = 1000000000
def add_gigasecond(date):
    return date + timedelta(seconds=GIGA_SECOND)
