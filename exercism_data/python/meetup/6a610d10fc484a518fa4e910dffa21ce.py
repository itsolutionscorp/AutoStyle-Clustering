from calendar import monthrange
from datetime import date


def meetup_day(year, month, day_of_the_week, which):
    """Return a day in the given year and month and on the given
    day of the week that fits the requirements set in 'which'.
    'which' may be one of the following:
        "teenth", "last", "1st", "2nd", "3rd" or "4th"
    """

    month_length = monthrange(year, month)[1]

    print month_length
    
    for day in range(1, month_length + 1):
    	days = date(year, month, day)
    
    print days

    for tp in days:
    	date = tp.strftime('%A')    	
    	if date == day_of_the_week:
    		print date

    candidates = [date_
                  for date_ in days_in_the_month
                  if day_name(date_) == day_of_the_week]

meetup_day(2013, 2, 'Saturday', 'teenth')
