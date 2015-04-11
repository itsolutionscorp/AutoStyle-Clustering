from calendar import Calendar



def meetup_day(year, month, day, mode):
  target_dates = date_isolate(year,month, day)

  if (mode == 'teenth'):
    for dates in target_dates:
      if (13 <= dates.day <= 19):
      	return dates

  elif (mode != 'last'):
    return target_dates[int(mode[0]) -1]
  
  else:
    return target_dates[-1]    



def date_isolate(year, month, day):
  isolated_list = []

  for date in Calendar().itermonthdates(year,month):
    if (date.month == month) and (date.strftime('%A') == day):
      isolated_list.append(date)
  
  return isolated_list




  



