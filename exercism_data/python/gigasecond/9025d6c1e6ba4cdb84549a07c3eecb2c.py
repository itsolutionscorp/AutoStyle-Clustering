from datetime import timedelta

def add_gigasecond(input_date):
    gigasecond = timedelta(0, 1000000000)
    return input_date + gigasecond
