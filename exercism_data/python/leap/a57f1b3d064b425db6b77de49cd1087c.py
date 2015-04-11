"""

```plain
on every year that is evenly divisible by 4
  except every year that is evenly divisible by 100
    unless the year is also evenly divisible by 400
```

For example, 1997 is not a leap year, but 1996 is.  1900 is not a leap
year, but 2000 is.

"""
#-*- coding:utf-8-*-
def is_leap_year(leap_year):
    if leap_year % 100 == 0 and leap_year % 400 != 0: 
        return False 
    elif leap_year % 4 == 0:
        return "%d is a leap year" % leap_year 
