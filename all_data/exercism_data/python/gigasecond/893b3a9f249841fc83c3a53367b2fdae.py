from datetime import date, timedelta

def add_gigasecond(start_date):
    delta = timedelta(seconds=1E9)
    end_date = start_date + delta
    return end_date
