from datetime import timedelta

def add_gigasecond(input_datetime):
    return input_datetime + timedelta(seconds = 1e9)
