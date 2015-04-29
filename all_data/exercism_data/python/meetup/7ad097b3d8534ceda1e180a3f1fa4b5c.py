__author__ = 'djdick'

days = {'monday': 0, 'tuesday': 1, 'wednesday': 2, 'thursday': 3, 'friday': 4, 'saturday': 5, 'sunday': 6}

def meetup_day(year, month, day, interval):
    first_day, numDays = calendar.monthrange(year, month)
