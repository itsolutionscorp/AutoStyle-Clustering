from datetime import timedelta

GIGASECOND_TIMEDELTA = timedelta(11574)

def add_gigasecond(input_date):
    return input_date+GIGASECOND_TIMEDELTA
