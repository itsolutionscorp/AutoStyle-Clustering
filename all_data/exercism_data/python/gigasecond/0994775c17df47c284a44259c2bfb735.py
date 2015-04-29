from datetime import timedelta 

def add_gigasecond(date):
    return date + timedelta(seconds=1e9) # adds timedelta object to the given date
    
