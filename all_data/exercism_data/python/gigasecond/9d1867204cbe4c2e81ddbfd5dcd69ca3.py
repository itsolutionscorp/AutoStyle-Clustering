from datetime import date, timedelta

def add_gigasecond(birthday):
    
    gigadays = 10 ** 9 / (60 * 60 * 24)
    
    return birthday + timedelta(days=gigadays)
