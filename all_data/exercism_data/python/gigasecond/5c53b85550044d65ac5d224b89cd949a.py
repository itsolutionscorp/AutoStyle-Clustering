GIGSEC = 10 ** 9

import datetime

def add_gigasecond(birthdate):
    sec = int(birthdate.strftime("%s")) += GIGSEC
    day = datetime.date.fromtimestamp(sec)

    return day
