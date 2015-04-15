from datetime import date, timedelta

WEEKDAYS = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}
ORDINALS = ['1st', '2nd', '3rd', '4th', '5th']
TEENS = [13, 14, 15, 16, 17, 18, 19]

def meetup_day(year, month, weekday, weekday_of_month):
    ## Get the first instance of the requested weekday in the requested month.
    month = date(year, month, 1)
    first_requested_weekday_date = get_first_requested_weekday(month, weekday)

    ## If this is a straightforward ordinal weekday (1st, 2nd etc), try to get the required date
    ## by going 7*(ordinal - 1) days into the future in this month. Raise an exception if the 
    ## requested date doesn't exist in this month.
    try:
        ordinal_modifier = ORDINALS.index(weekday_of_month)
    except ValueError:
        ordinal_modifier = -1

    if(ordinal_modifier != -1):
        try:
            requested_weekday_date = first_requested_weekday_date.replace(day=first_requested_weekday_date.day + 7*ordinal_modifier)
        except ValueError:
            raise MeetupDayException("Boo!")
        return requested_weekday_date

    ## If this isn't an ordinal weekday (so if it's a 'last' or a 'teenth'), make a list of 
    ## all the weekdays in this month which, for each weekday, contains information on whether
    ## it's the last or teenth weekday of that month. Then traverse that list to return the date
    ## of the teenth requested weekday or last requested weekday as appropriate.
    weekdays = make_weekdays(first_requested_weekday_date)
    if weekday_of_month == 'teenth':
        for weekday in weekdays:
            if weekday.teenth:
                return weekday.date

    if weekday_of_month == 'last':
        for weekday in weekdays:
            if weekday.last:
                return weekday.date

    ## If the code gets this far then it means that the requested date isn't an ordinal, 
    ## a teenth, or a last. There isn't any logic to handle those cases so just raise an exception
    raise MeetupDayException("Exception!")


def get_first_requested_weekday(month, requested_weekday):
	"""Return the date of the first requested weekday of the specified month."""
	requested_weekday_number = WEEKDAYS[requested_weekday]
	first_weekday = month.weekday()
	first_requested_weekday_diff = (requested_weekday_number - first_weekday) % 7

	return month + timedelta(days=first_requested_weekday_diff)

def make_weekdays(first_requested_weekday_date):
    """Make a list of Weekday objects in this month for this weekday."""
    weekdays = []
    weekday_date = first_requested_weekday_date
    for ordinal in ORDINALS:
        weekday = Weekday(weekday_date, ordinal, weekday_date.day in TEENS)
        weekdays.append(weekday)
        try:
            weekday_date = weekday_date.replace(day = weekday_date.day + 7)
        except ValueError:
            break
    return weekdays

class Weekday:
    def __init__(self, date, ordinal, teenth):
        self.date = date
        self.teenth = teenth
        self.last = self.is_last(ordinal)

    def is_last(self, ordinal):
        if ordinal == '5th':
            return True
        elif ordinal == '4th':
            try:
                self.date.replace(day=self.date.day + 7)
                return False
            except ValueError:
                return True
        else:
            return False

    def __repr__(self):
        items = ("%s = %r" % (k, v) for k, v in self.__dict__.items())
        return "<%s: {%s}>" % (self.__class__.__name__, ', '.join(items))

class MeetupDayException(Exception):
    pass
