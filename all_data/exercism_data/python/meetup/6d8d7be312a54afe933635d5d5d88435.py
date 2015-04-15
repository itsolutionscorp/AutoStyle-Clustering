import datetime

range_map = {
     "1st":     range(1,8),
     "2nd":     range(8,15),
     "3rd":     range(15,22),
     "4th":     range(22,29),
     "teenth":  range(13,20),
     "last":    range(31, 0, -1)}

day_map = {
    "Monday":       0,
    "Tuesday":      1,
    "Wednesday":    2,
    "Thursday":     3,
    "Friday":       4,
    "Saturday":     5,
    "Sunday":       6}
    
def meetup_day(year, month, day, week_string):
    meeting_day = day_map[day]
    test_date = datetime.date(year, month, 1)
    for test_day in range_map[week_string]:
        try:
            test_date = test_date.replace(day=test_day)
            if test_date.weekday() == meeting_day:
                return test_date
        except ValueError: pass
    
    
