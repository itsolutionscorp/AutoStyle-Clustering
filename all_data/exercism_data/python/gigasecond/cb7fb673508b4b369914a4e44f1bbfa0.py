import datetime


def add_gigasecond(birthday):
    ''' given a person's birthday, returns their gigasecond anniversary '''
    gigasecond = datetime.timedelta(seconds=10**9)
    return birthday + gigasecond
