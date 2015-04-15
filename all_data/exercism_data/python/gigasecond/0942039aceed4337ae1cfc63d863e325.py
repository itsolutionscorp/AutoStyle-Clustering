from datetime import date

def add_gigasecond(startdate):
    
    start = startdate.strftime('%s')
    end = int(start) + (10**9)
    
    return date.fromtimestamp(end)
