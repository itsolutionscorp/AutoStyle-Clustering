#insert any year and determine if it is a leap year

def is_leap_year(year):

  skipLeap = year %100 == 0 and  year %400 != 0

  return year %4 == 0 and not skipLeap
