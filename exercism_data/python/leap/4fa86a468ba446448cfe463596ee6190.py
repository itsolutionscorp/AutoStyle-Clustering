def is_leap_year(year):
  divis_by_4 = year % 4 == 0
  divis_by_100 = year % 100 == 0
  divis_by_400 = year % 400 == 0

  return divis_by_4 and (not divis_by_100 or divis_by_400)
