import datetime

def add_gigasecond(birthday):
    gigasecond = datetime.timedelta(seconds=+10**9)
    gigabirthday = birthday + gigasecond
    return gigabirthday