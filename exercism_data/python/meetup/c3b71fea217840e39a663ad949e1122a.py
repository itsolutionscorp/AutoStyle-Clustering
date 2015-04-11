from calendar import Calendar, day_name; C = Calendar()
import datetime as dt

def meetup_day(year, month, name, ordinal):
    
    day_index = [k for k, v in enumerate(day_name) if name == v]

    day_candidates = [k for k, v in list(C.itermonthdays2(year, month))
                        if v == day_index[0] and k != 0]
        
    for k, v in enumerate(day_candidates):
        if ordinal == 'teenth' and v in xrange(13, 20):
            day = v
        elif ordinal == 'last':
            day = day_candidates[::1].pop()
        else:
            try:
                if k == int(ordinal[0])-1:
                    day = v
            except ValueError:
                pass
                   
    return dt.date(year, month, day)
