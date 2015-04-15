###
### Leapyear python exercise
###
import math

def is_leap_year(year):

     ###Check to see if we have a year C.E. with 4-digit values or less...
     date = int(year)
     if math.floor(math.log10(date))+1 <= 4:

          ###Check for century years, then apply the 400 rule...
          if date % 100 == 0:
               if date % 400 == 0:
                    return True
               else:
                    return False

          ###If we get this far, if divisible by 4, leapyear,
          ###if not, not a leapyear...
          elif date % 4 == 0:
               return True            
          else:
               return False  

     ###If we don't get a year object passed to us, its an error...
     else:
          return None
