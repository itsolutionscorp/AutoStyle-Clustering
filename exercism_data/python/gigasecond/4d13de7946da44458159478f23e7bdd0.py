#*-*coding: utf-8 *-*
from datetime import timedelta, datetime
def add_gigasecond(my_birthday):
   return timedelta(seconds=10**9) + my_birthday

if __name__ == '__main__':
  year = int(raw_input())
  month = int(raw_input())
  day = int(raw_input())
  print add_gigasecond(date(year, month, day)) 
