from datetime import date

def add_gigasecond(bday):
    return date.fromordinal(date.toordinal(bday)+(10**9/60/60/24))
