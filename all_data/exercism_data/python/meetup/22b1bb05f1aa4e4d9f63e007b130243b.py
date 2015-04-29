from datetime import date
import calendar

def meetup_day(year, month, wod, cond):
    
    teens = [13, 14, 15, 16, 17, 18, 19]
    
    wods = ['Monday', 'Tuesday', 'Wednesday', 'Thursday',
            'Friday', 'Saturday', 'Sunday'] 
    
    assert wod in wods 
    assert cond in ['1st', '2nd', '3rd', '4th', '5th', 'last', 'teenth']
    
    wod_id = dict(zip(wods, range(7))) 

    selected_month = calendar.Calendar().monthdays2calendar(year, month)
    selected_weekday = [selected_month[i][wod_id[wod]] for i in xrange(len(selected_month))]
    clean_weekday = [s[0] for s in selected_weekday if (s[0] is not 0) ]
    
    if cond == '1st':
        day = clean_weekday[0]
    elif cond == '2nd':
        day = clean_weekday[1]
    elif cond == '3rd':
        day = clean_weekday[2]
    elif cond == '4th':
        day = clean_weekday[3]
    elif cond == '5th':
        day = clean_weekday[4]
    elif cond == 'last':
        day = clean_weekday[-1]
    elif cond == 'teenth':
        day = [d for d in clean_weekday if d in teens][0]
        
    return date(year, month, day)
