from datetime import timedelta


def add_gigasecond(date_of_birth):
    return date_of_birth + timedelta(0, 10**9)
