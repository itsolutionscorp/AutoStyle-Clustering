from datetime import date, timedelta

def add_gigasecond(date):
 minute=60
 hour=60*minute
 day=24*hour
 length=1e9
 offset=length/day
 return date+timedelta(offset)
