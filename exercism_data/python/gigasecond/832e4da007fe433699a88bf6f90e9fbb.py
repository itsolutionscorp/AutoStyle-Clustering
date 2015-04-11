import datetime 

def add_gigasecond(date):
    
    added_time=date+datetime.timedelta(0,10**9)
    return added_time
