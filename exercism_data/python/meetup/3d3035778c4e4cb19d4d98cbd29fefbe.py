from datetime import date, timedelta

def meetup_day(year, month, weekday, position):
    date_range = {
                  '1st': range(1,8),
                  '2nd': range(8,15), 
                  'teenth': range(13,20),
                  '3rd': range(15,22),
                  '4th': range(22,29)
                 }
    if position in ['1st', '2nd', '3rd', '4th', 'teenth']:
        for day in date_range[position]:
            if date(year, month, day).strftime('%A') == weekday:
                return date(year, month, day)
    else:
        end_date = date(year, month+1, 1) - timedelta(days=1) 
        for day in range(end_date.day-6,end_date.day+1):
            if date(year, month, day).strftime('%A') == weekday:
                return date(year, month, day)
