from datetime import timedelta


gigasecond = 10 ** 9

def add_gigasecond(_datetime):
    return _datetime + timedelta(seconds=gigasecond)
