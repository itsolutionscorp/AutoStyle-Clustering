from datetime import timedelta

def add_gigasecond(birth_date):
    gigaday = birth_date + timedelta(seconds = 1e9)
    return gigaday
