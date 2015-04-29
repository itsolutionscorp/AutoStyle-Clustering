from datetime import date, timedelta

def add_gigasecond(birthdate):
    if isinstance(birthdate,date):
        return birthdate + timedelta(seconds=10**9)
    else:
        return None
