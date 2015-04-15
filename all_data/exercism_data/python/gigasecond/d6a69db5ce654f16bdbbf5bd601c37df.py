from datetime import datetime as dt
import time

def add_gigasecond(date):
    epoch_time = time.mktime(time.strptime(date.isoformat(), "%Y-%m-%d"))
    epoch_time += 10**9
    return dt.fromtimestamp(epoch_time).date()
