from datetime import timedelta, date

def add_gigasecond(date):
    dif = timedelta(days=(pow(10,9) / 60 / 60 / 24))

    return date + dif
