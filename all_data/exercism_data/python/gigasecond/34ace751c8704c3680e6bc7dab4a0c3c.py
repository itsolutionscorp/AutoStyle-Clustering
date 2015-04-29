from datetime import timedelta

def add_gigasecond(date):
    return date+timedelta(seconds= 1e9) #e is exponential; int(1e9) == 10**9
