from datetime import timedelta

#Function adds 1 billion seconds(gigasecond) to a date
def add_gigasecond(date):
    return date + timedelta(seconds = 10**9)
