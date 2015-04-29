from datetime import date 
import calendar 

def meetup_day(year, month, week_day, week_of_meetup):

    def get_calendar():
        return calendar.Calendar().monthdayscalendar(year, month)

    def get_day_as_number():
        return getattr(calendar, week_day.upper())

    def get_week_as_number(*args):
        
        cal = args[0]
        meetup_day_as_number = args[1]
        week_of_meetup = args[2]

        is_day_in_first_week = cal[0][meetup_day_as_number] != 0

        if week_of_meetup == '1st':
            if is_day_in_first_week:
                return 0
            else: 
                return 1
        if week_of_meetup == '2nd':
            if is_day_in_first_week:
                return 1
            else:
                return 2
        if week_of_meetup == '3rd':
            if is_day_in_first_week:
                return 2
            else: 
                return 3
        if week_of_meetup == '4th':
            if is_day_in_first_week:
                return 3
            else:
                return 4
        if week_of_meetup == 'last':
            return len(cal) - 1
        if week_of_meetup == 'teenth':
            i = 0
            for calendar_week in cal:
                if calendar_week[meetup_day_as_number] in range(13, 20):
                    return i
                else:
                    i += 1 

    def get_day_date_of_meetup():

        cal = get_calendar()
        
        d = get_day_as_number()
        w = get_week_as_number(cal, d, week_of_meetup)
                        
        return cal[w][d]


    return date(year, month, get_day_date_of_meetup())
