from time import mktime
from datetime import date

GIGASECOND = 10**9


def add_gigasecond(bday):
    bday_giga = mktime(bday.timetuple()) + GIGASECOND

    return date.fromtimestamp(bday_giga)
