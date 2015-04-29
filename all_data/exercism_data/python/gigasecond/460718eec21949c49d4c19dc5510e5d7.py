import datetime

def add_gigasecond(date_of_birth):
    end = date_of_birth + datetime.timedelta(0,1000000000)
    return datetime.date(end.year, end.month, end.day)

