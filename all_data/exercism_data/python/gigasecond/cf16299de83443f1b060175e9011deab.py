from datetime import datetime, timedelta

def add_gigasecond(d):
    return (datetime.combine(d,datetime.min.time())+ timedelta(0,10**9)).date()
