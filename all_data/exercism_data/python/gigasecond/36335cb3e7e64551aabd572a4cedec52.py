import datetime

def add_gigasecond(datum):
    giga = 10 ** 9
    gigaLater = datum + datetime.timedelta(seconds = giga)
    return gigaLater
