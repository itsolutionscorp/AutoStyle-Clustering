from datetime import *

def add_gigasecond(init):
    return (datetime.combine(init, time())+timedelta(seconds=10**9)).date()
