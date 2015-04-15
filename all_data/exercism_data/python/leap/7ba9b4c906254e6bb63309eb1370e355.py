#  on every year that is evenly divisible by 4
#  except every year that is evenly divisible by 100
#  unless the year is also evenly divisible by 400

#!/usr/bin/python

year = raw_input('What year do you want to test? ')

if int(year)%4==0 and (int(year)%100!=0 or int(year)%400==0):
	print "yep"
else:
	print "nope"
