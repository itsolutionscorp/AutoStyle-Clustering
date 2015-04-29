# Leap year exercise
# Written in 2014 by Benjamin Shyong <hello@benshyong.com>

def is_leap_year(year):
  return year%400==0 or (year%4==0 and year%100!=0)
