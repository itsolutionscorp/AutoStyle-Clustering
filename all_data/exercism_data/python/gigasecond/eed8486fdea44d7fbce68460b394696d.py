from datetime import date
from datetime import timedelta
class Gigasecond:
    """ Gigasecond computes when someone will celebrate their 1GSec birthday"""
    DAYS_IN_GSEC = 10**9 / (3600*24)
    def __init__(self, date):
        deltaDays = timedelta(days = Gigasecond.DAYS_IN_GSEC)
        self.date = date + deltaDays
