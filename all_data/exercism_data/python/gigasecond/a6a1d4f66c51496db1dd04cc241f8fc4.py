import datetime

def add_gigasecond(birth_date):
    days = 10**9/((60*60)*24)
    gsecs = birth_date + datetime.timedelta(days)
    return gsecs
