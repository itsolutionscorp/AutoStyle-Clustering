import datetime


def add_gigasecond(birthdate):
    gigasecond = datetime.timedelta(seconds=10**9)
    # if isinstance(birthdate, datetime.date):
    #    t = datetime.time(0,0)
    #    d = datetime.datetime.combine(birthdate, t)
    #    print d
    #else:
    #    d = birthdate
    d = birthdate
    return d + gigasecond
