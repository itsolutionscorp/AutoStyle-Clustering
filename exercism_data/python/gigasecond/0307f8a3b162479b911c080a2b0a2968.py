import datetime

def add_gigasecond( start_date ):
    gigasecond = datetime.timedelta( seconds = 10 ** 9 )
    return start_date + gigasecond
