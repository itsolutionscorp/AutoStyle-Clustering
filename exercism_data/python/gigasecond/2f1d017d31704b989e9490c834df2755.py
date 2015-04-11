from datetime import datetime
from datetime import timedelta
from datetime import date

def add_gigasecond(date):
    newdate=datetime.combine(date, datetime.min.time())
    time=1000000000 #seconds
    age=timedelta(seconds=time) # days
    print age
#    age=datetime(date.year,date.month,date.day)+age
    age=newdate+age
    return age.date()
    
    
