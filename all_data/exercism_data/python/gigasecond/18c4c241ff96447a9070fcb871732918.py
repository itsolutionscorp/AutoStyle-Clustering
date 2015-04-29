import datetime

def add_gigasecond(date):
    seconds_born = int(date.strftime('%s'))

    gigasecond_birthday = seconds_born + 10**9

    return datetime.date.fromtimestamp(gigasecond_birthday)
