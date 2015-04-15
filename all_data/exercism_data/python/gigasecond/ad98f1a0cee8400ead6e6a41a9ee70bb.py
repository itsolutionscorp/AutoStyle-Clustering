import arrow


def add_gigasecond(input_date):
    return arrow.get(input_date).replace(seconds=10**9).date()
