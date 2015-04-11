__author__ = 'angelo'
from datetime import date, timedelta

class Gigasecond:

    def __init__(self, birth_date):
        self.date = birth_date + timedelta(seconds=1000000000)
