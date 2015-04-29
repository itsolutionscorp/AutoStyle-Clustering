import datetime 

def add_gigasecond(inDate):
    gigasecond = datetime.timedelta(seconds=1e9)
    return inDate + gigasecond
