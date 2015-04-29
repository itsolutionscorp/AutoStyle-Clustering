from datetime import date, timedelta
def add_gigasecond(birthday):
    gigasecond = 1000000000
    gigasecondInDays = gigasecond / 3600 / 24
    return birthday + timedelta(days=gigasecondInDays)
