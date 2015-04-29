from datetime import datetime  
from datetime import timedelta  
def add_gigasecond(date):
    giga_date = date + timedelta(seconds=10**9)

    return giga_date
