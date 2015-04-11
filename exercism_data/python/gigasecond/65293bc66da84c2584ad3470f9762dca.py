from datetime import date,timedelta

def add_gigasecond(date):
    time_to_add = timedelta(seconds = 10**9)
    return date + time_to_add

#python has functions for everything
#such beauty
