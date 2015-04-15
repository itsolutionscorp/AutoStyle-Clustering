from datetime import timedelta
gigasecond = timedelta(seconds=10**9)


def add_gigasecond(start_date):
    return start_date + gigasecond
