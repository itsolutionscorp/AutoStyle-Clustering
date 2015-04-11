from datetime import timedelta

def add_gigasecond(date_of_birth):
    return date_of_birth + timedelta(seconds=10**9)
