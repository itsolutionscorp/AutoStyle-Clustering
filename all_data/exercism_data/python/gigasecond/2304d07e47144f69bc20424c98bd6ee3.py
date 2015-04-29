from datetime import timedelta


# method to calculate a date 1 giga second from date passed to input
def add_gigasecond(dateToCheck):
    return dateToCheck + timedelta(seconds=(10**9))
