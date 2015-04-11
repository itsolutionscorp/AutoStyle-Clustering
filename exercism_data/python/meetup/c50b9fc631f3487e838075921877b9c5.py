from datetime import date


def meetup_day(year, month, dayOfWeek, nth):
    convert = { 'Monday'    :   0,\
                'Tuesday'   :   1,\
                'Wednesday' :   2,\
                'Thursday'  :   3,\
                'Friday'    :   4,\
                'Saturday'  :   5,\
                'Sunday'    :   6   }
    
    converNth = {   '1st'   :   1,\
                    '2nd'   :   2,\
                    '3rd'   :   3,\
                    '4th'   :   4   }

    firstDay = date(year, month, 1).weekday()
    return date(2014, 10, 6)
