from datetime import date, timedelta

def add_gigasecond(date):
    ''' adds one gigasecond to a given date '''

    return date + timedelta(seconds = 1e9)
