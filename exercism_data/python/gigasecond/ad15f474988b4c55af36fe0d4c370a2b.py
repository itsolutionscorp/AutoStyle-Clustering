from datetime import timedelta


class Gigasecond:
    def __init__(self, date):
        self.date = date + timedelta(seconds=1e9)
