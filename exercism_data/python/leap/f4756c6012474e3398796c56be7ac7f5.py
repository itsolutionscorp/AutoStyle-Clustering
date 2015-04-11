def is_leap_year(yr):
   if (yr % 100 == 0 and yr % 400 != 0) or yr % 4 !=0:
       return False
   return True
