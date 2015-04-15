#gigasecond.py
import datetime

def add_gigasecond(bday):
    # bday is a date from datetime module (yr, month, day)
#    minPerSec = 1.0/60.0
#    hrPerMin = 1.0/60.0
#    dayPerHr = 1.0/24.0
#    gigasecInDays = (10**9)*minPerSec*hrPerMin*dayPerHr
    
    time_to_add = datetime.timedelta(seconds = 10**9)
    
    return bday + time_to_add    

    
