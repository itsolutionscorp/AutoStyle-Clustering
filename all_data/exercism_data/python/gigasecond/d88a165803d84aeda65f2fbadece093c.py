import datetime as dt

def add_gigasecond(init):
    new_dt = dt.datetime.combine(init, dt.time()) + \
             dt.timedelta(seconds=1000000000)

    return new_dt.date()
