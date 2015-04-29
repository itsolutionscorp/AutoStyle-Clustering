import datetime
def is_leap_year(y):
    d = datetime.date(y, 2, 28)
    d = d + datetime.timedelta(days=1)
    return d.month == 2
