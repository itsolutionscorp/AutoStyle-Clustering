from datetime import datetime, timedelta

def add_gigasecond(birthdate):

    gigabirthday = birthdate + timedelta(seconds=1e9)

    return gigabirthday
