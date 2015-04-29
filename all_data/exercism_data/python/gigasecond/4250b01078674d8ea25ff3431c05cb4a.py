from datetime import date
from datetime import timedelta


def add_gigasecond(start_date):
    return start_date + timedelta(seconds=1000000000)

if __name__ == '__main__':
    print add_gigasecond(date(2011, 4, 25))
