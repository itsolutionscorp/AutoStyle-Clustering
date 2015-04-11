from datetime import datetime, timedelta

GIGASECOND = timedelta(seconds=10 ** 9)


def add_gigasecond(date: datetime) -> datetime:
    return date + GIGASECOND
