
from datetime import date

_DAYS_IN_GIGASECOND = int(10**9 / 60 / 60 / 24)

def add_gigasecond(birth):
    return date.fromordinal(birth.toordinal() + _DAYS_IN_GIGASECOND)
