#
#
#
def is_leap_year(year):
   result = ''
   dif = year - 1996
   if dif%4 == 0:
     if year%100== 0 and year%400 ==0:
     	result = True
     elif year%100 ==0 and year%400 !=0:
        result = False
     else :
        result = True
   else :
     result = False
   return result
