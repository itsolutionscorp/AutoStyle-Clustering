import datetime

class Gigasecond:
    def __init__(self, date):
        self.date = (date + datetime.timedelta(seconds = 10 ** 9))
