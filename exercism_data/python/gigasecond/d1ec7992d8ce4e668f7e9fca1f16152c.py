from datetime import timedelta

gigasecond = 10 ** 9

def add_gigasecond(date):
    return date + timedelta(seconds = gigasecond)
