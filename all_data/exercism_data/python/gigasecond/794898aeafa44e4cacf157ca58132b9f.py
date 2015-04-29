from datetime import date

def add_gigasecond(startdate):
    
    return date.fromtimestamp(int(startdate.strftime('%s')) + (10**9))
