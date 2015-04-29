from datetime import timedelta

def add_gigasecond(start_time):
    return start_time + timedelta(seconds=1000000000)
