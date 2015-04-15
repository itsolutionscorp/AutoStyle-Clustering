from _datetime import timedelta



def add_gigasecond(birthday):
    return timedelta(seconds=10**9) + birthday
