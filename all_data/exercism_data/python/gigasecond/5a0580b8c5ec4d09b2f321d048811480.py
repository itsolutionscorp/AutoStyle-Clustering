from time import mktime
from datetime import date

def add_gigasecond(bday):
    bday_tuple = bday.timetuple()
    bday_unix = mktime(bday_tuple) + 10**9
    anniversary = date.fromtimestamp(bday_unix)
    return anniversary
