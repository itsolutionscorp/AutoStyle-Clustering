from datetime import date


def add_gigasecond(date):
    total_days = (10**9)/(60*60*24)
    return date.fromordinal(date.toordinal() + int(total_days))
