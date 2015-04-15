from datetime import date
import calendar

def meetup_day(md_year, md_month, md_day, md_seq):
    md_cal = calendar.monthcalendar(md_year, md_month)
    weeks_in_month = len(md_cal)
    weekdays = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
    inner_i = 0
    outer_i = 1
    md_date = 0
    offset = 0
    
    for i in range (len(weekdays)):
        if weekdays[i] == md_day:
            inner_i = i
            break
    
    if md_cal[0][inner_i] == 0:
        offset = 1
        
    if md_seq == '1st':
        if offset:
            md_date = md_cal[1][inner_i]
        else:
            md_date = md_cal[0][inner_i]
            
    elif md_seq == '2nd':
        if offset:
            md_date = md_cal[2][inner_i]
        else:
            md_date = md_cal[1][inner_i]
    
    elif md_seq == '3rd':
        if offset:
            md_date = md_cal[3][inner_i]
        else:
            md_date = md_cal[2][inner_i]
            
    elif md_seq == '4th':
        if offset:
            md_date = md_cal[4][inner_i]
        else:
            md_date = md_cal[3][inner_i]
            
    elif md_seq == 'last':
        if md_cal[weeks_in_month-1][inner_i]:
            md_date = md_cal[weeks_in_month-1][inner_i]
        else:
            md_date = md_cal[weeks_in_month-2][inner_i]
    
    else: #must be teenth
        while md_cal[outer_i][inner_i] < 13:
            outer_i += 1
        md_date = md_cal[outer_i][inner_i]
    
    return date(md_year, md_month, md_date)
