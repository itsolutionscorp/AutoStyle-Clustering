from datetime import date
from datetime import timedelta

def add_gigasecond(birthdate):
    gigasecond = (10**9)
    delta = timedelta(seconds=gigasecond)
    gigasecondAnniversary = birthdate + delta
    return gigasecondAnniversary
