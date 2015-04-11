from datetime import date, timedelta

def gigasecond_to_days():
    return 10**9 / (60 * 60 * 24)

def add_gigasecond(d):
    days = gigasecond_to_days()
    return d + timedelta(days)

print add_gigasecond(date(1982,7,6))
