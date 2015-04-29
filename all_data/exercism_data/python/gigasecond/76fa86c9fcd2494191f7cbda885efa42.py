from datetime import timedelta
def add_gigasecond(time):
    return time + timedelta(seconds=pow(10,9))
