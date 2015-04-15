from datetime import date
import calendar

def meetup_day(year, month, day, ordinal):
    
    calendar.setfirstweekday(calendar.SUNDAY)
    day_of_week = {'Sunday': 0, 'Monday': 1, 'Tuesday': 2, 'Wednesday': 3,
                    'Thursday': 4, 'Friday': 5, 'Saturday': 6}
    
    day_index = day_of_week[day]
    days_of_month = [week[day_index] for week in calendar.monthcalendar(year, month)
                                     if week[day_index] != 0]

    
    day_map = {'1st': days_of_month[0],
                   '2nd': days_of_month[1],
                   '3rd': days_of_month[2],
                   '4th': days_of_month[3],
                   'last': days_of_month[-1],
                   'teenth': filter(lambda a: 13<=a<=19, days_of_month)[0]}

        
    return date(year, month, day_map[ordinal])
