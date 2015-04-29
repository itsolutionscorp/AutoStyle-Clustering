def is_leap_year(year):
  evenly_divided_by_4 = year % 4 == 0
  evenly_divided_by_100 = year % 100 == 0
  evenly_divided_by_400 = year % 400 == 0

  is_leap = False

  if evenly_divided_by_4:
    is_leap = True

    if evenly_divided_by_100:
      is_leap = False

      if evenly_divided_by_400:
        is_leap = True

  return is_leap
