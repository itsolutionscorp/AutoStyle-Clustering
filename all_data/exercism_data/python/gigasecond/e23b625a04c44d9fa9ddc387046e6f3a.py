import datetime

gigasecond_as_days = (10**9) / (60 * 60 * 24)

def add_gigasecond(date):
    days = date.toordinal()
    days += gigasecond_as_days
    return datetime.date.fromordinal(days)
