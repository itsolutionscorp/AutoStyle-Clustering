from datetime import datetime, timedelta

GIGASECOND = timedelta(seconds=1e9)


def add_gigasecond(date: datetime) -> datetime:
    return date + GIGASECOND
