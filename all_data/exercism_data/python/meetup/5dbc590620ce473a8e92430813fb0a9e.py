from datetime import date
from calendar import Calendar

def meetup_day(year, month, weekday, specifier):
    cal = Calendar()
    selectors = {
        '1st': lambda days: days[0],
        '2nd': lambda days: days[1],
        '3rd': lambda days: days[2],
        '4th': lambda days: days[3],
        'last': lambda days: days[-1],
        'teenth': lambda days: next((day for day in days if day.day > 9))
    }
    preselected_days = filter(lambda day: day.strftime('%A') == weekday and day.month == month, cal.itermonthdates(year, month))
    return selectors[specifier](preselected_days)
