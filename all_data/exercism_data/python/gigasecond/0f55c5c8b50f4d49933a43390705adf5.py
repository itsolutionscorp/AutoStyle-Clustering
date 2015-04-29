from datetime import datetime
 
def add_gigasecond(date_from):
    return datetime.date(datetime.fromtimestamp(int(date_from.strftime('%s')) +  10**9))
