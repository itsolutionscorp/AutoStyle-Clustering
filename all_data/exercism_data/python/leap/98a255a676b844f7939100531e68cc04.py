'''
Determines whether year is leap year.
Logic:
  on every year that is evenly divisible by 4
    except every year that is evenly divisible by 100
      unless the year is also evenly divisible by 400

Comments: Had issues figuring out the best way to lay out these booleans. I still think there is a better way to do this than setting leap four separate times.
'''
def is_leap_year(year):
  leap = False
  if (year % 4) == 0:
    leap = True
  if (year % 100) == 0:
    leap = False
    if (year % 400) == 0:
      leap = True
  return leap
