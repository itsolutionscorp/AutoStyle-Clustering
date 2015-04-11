from datetime import timedelta


def add_gigasecond(datetime):
    gigasecond = timedelta(seconds=10 ** 9)
    return datetime + gigasecond
