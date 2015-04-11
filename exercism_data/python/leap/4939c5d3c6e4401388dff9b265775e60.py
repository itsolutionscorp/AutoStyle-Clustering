

def is_leap_year(year):
  """Return True if year is a leap year.

  A leap year is a special kind of year which occurs once every four years,
  which has 366 days including 29 February as an intercalary day.
  """

  if year % 4 != 0:
    return False

  if year % 100 == 0:
    return year % 400 == 0

  return True
