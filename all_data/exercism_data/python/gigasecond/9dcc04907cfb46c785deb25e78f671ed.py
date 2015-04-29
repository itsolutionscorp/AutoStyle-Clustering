from datetime import date
import time

def add_gigasecond(birthday):
    birthsecond = time.mktime(birthday.timetuple())

    return date.fromtimestamp(birthsecond + (10 ** 9))
