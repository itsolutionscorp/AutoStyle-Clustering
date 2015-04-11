from datetime import timedelta

class Gigasecond(object):
    as_timedelta = timedelta(seconds = 1e9)

    def __init__(self, birthdate):
        self.date = birthdate + Gigasecond.as_timedelta
