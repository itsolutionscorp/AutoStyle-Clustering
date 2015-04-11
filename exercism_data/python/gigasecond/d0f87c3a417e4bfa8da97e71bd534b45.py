import datetime

def add_gigasecond(birthday):
    today = datetime.date.today()
    return today + datetime.timedelta(0, (10**9 - (today-birthday).total_seconds() ))
