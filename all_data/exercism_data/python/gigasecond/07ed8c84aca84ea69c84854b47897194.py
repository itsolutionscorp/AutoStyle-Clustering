import time
from datetime import date

def add_gigasecond(date_object):

    struct_time = time.strptime(date_object.__str__(),"%Y-%m-%d")
    
    # Converts struct time format to seconds after epoch
    
    date_in_seconds = time.mktime(struct_time)
    
    gigasec = date_in_seconds + 10**9
    
    # Coverts seconds since epoch to a date
    
    new_struct_time = date.fromtimestamp(gigasec)
    
    return new_struct_time
    
