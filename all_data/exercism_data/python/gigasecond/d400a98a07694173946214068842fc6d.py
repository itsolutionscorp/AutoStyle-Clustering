import datetime



def add_gigasecond(birthday):
    return datetime.timedelta(seconds=10**9) + datetime.date(*map(int, str(birthday).split('-')))
