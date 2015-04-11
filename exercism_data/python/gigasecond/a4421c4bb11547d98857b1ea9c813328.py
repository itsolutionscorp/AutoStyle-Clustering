from datetime import timedelta

def add_gigasecond(date):
    """returns the date plus a gigasecond"""
   
    gigasec = timedelta(seconds=1000000000)
    return date + gigasec
