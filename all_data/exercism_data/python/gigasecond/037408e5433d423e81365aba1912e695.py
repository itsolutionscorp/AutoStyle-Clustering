from datetime import date
from datetime import timedelta
def add_gigasecond(input):
    return input + timedelta(days = 10 ** 9 / (24 * 60 * 60))
