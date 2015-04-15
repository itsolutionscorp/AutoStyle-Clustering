from datetime import timedelta, datetime


class date:
    def __init__(self, year, month, day):
        self.year = year
        self.month = month
        self.day = day
        self.date_time = datetime(year, month, day)
    @property
    def __repr__(self):
        return datetime(self.year, self.month, self.day)

def add_gigasecond(date_class):
    b = date_class + timedelta(minutes=10 ^ 7)
    return b
