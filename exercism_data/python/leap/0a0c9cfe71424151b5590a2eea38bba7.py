def is_leap_year(year):
  evenly_divided_by_4 = year % 4 == 0
  evenly_divided_by_100 = year % 100 == 0
  evenly_divided_by_400 = year % 400 == 0

  return evenly_divided_by_4 and (!evenly_divided_by_100 or evenly_divided_by_400)
