from datetime import date, timedelta


SECONDS_PER_DAY = 60 * 60 * 24
GIGA = 1e9


class Gigasecond(object):

    """One gigasecond anniversary"""

    def __init__(self, birthday):
        """Create a new gigasecond anniversary for the given birthday."""
        self.date = birthday + timedelta(days=GIGA/SECONDS_PER_DAY)
