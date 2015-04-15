from datetime import datetime,timedelta

def add_gigasecond(date):
    seconds = 10**9
    days = (((seconds)/60)/60)/24
    
    anniversary = date + timedelta(days=days)
    return anniversary
