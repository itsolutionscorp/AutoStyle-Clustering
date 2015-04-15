from datetime import timedelta

_BILLION_SECONDS_DELTA = timedelta(seconds = 1e9)

def add_gigasecond(input_datetime):
    return input_datetime + _BILLION_SECONDS_DELTA
