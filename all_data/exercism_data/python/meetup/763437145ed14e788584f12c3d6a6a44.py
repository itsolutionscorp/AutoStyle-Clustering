from datetime import date
from datetime import timedelta
import calendar

WEEKDAY_MAPPING = {'Monday':0,'Tuesday':1,'Wednesday':2,'Thursday':3,'Friday':4,'Saturday':5,'Sunday':6}
POSSIBLE_OCCURRENCES = ['1st','2nd','3rd','4th','last','teenth']

def meetup_day(year, month, weekday, occurrence):

    if validate_param(year,int) and \
        validate_param(month,int) and \
        validate_param(weekday,basestring) and \
        validate_param(occurrence,basestring) and \
        validate_range_of_values(month,range(1,12)) and \
        validate_range_of_values(weekday,WEEKDAY_MAPPING.keys()) and \
        validate_range_of_values(occurrence,POSSIBLE_OCCURRENCES):

        return get_weekday_occurrence(date(year,month,1),WEEKDAY_MAPPING[weekday],occurrence)
    else:
        return None

def get_weekday_occurrence(base_date, weekday, occurrence):
    # Advance base_date until we get to the first occurrence of this weekday
    while(base_date.weekday() != weekday):
        base_date = base_date + timedelta(days=1)

    # Get the first weekday in the month, as well as the last day in the month
    first_weekday_in_month,last_day_in_month = calendar.monthrange(base_date.year,base_date.month)

    if occurrence in ('1st','2nd','3rd','4th'):
        return base_date + timedelta(days=7*(int(occurrence[0]) - 1))
    else:
        # Since we're sharing the same logic for both 'last' and 'teenth' cases,
        # in the case of 'teenth' we set the 'last' day of the month to be 19 (i.e. the last 'teenth' value)
        if occurrence == 'teenth':
            last_day_in_month = 19

        current_day = base_date.day

        # Go forward 1 week at a time, until we get to either:
        # - The last occurrence of this weekday in this month
        # - The 'teenth' weekday in the month
        while (current_day + 7 <= last_day_in_month):
            current_day += 7

        # Return the base date, plus the number of days we moved forward
        return base_date + timedelta(days=current_day - base_date.day)

def validate_param(object, type):
    return isinstance(object,type)

def validate_range_of_values(value,possible_values):
    return value in possible_values
