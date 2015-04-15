import datetime

def add_gigasecond(date):
    try:
        end = date + datetime.timedelta(seconds=10**9)
        return end
    except TypeError:
        print('Parameter must be a date object')
