#  basic function to test if year if a leap year, returns True if is leap year.

# COMPARISON RULES FOR LEAP YEAR CONDITION
# on every year that is evenly divisible by 4
# except every year that is evenly divisible by 100
# unless the year is also evenly divisible by 400

def is_leap_year(yearToCheck):
   if (yearToCheck % 400 == 0):
      return True
   elif (yearToCheck % 100 == 0):
      return False
   elif (yearToCheck % 4 == 0):
      return True
   else:
      return False
