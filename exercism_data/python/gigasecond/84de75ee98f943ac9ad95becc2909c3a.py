from datetime import date, timedelta

gigasec = timedelta(seconds=10**9)

def add_gigasecond(start_date):
    return start_date + gigasec
