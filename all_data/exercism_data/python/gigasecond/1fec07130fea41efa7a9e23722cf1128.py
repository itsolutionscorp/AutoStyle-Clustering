import datetime


def add_gigasecond(date_val):

    date = datetime.timedelta(seconds=10 ** 9)
    result_date = date_val + date

    return result_date
