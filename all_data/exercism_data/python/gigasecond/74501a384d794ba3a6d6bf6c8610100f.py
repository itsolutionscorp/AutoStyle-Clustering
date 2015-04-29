from datetime import date
import time
def add_gigasecond(d1):
    t2=time.strptime(d1.strftime("%a %b %d %H:%M:%S %Y"))
    t1=time.mktime(t2)

    return date.fromtimestamp(t1+10**9)
