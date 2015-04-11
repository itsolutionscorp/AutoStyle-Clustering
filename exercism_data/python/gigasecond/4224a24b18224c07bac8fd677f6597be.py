from datetime import date,timedelta
def add_gigasecond(startdate):
    """"add 10**9 seconds to a given date"""
    #is this cheating? reading the datetime docs...
    return startdate + timedelta(0,10**9)
