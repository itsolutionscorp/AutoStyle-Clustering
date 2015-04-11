#Is a year a leap year 

year = int(input("Enter a year: "))

if (year % 4) == 0:
   if (year % 100) == 0:
       if (year % 400) == 0:
           print("{0} is a leap year, yay!".format(year))
       else:
           print("{0} isnt a leap year".format(year))
   else:
       print("{0} is a leap year".format(year))
else:
   print("{0} isnt a leap year".format(year))
