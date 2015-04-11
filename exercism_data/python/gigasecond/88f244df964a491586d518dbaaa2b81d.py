from datetime import (date, timedelta)


_gigasecs = timedelta(seconds=1000000000)


def add_gigasecond(day):
    return day + _gigasecs
