from datetime import timedelta

def add_gigasecond(dateobject):
    return dateobject + timedelta(0, 10**9)
