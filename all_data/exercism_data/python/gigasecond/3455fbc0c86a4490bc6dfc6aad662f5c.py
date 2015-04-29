#*-*coding: utf-8 *-*
from datetime import timedelta, datetime
def add_gigasecond(my_birthday):
   gigasecond = timedelta(seconds=10**9)
   birthday_and_gigasecond = my_birthday + gigasecond
   return birthday_and_gigasecond

if __name__ == '__main__':
  year = int(raw_input())
  month = int(raw_input())
  day = int(raw_input())
  print add_gigasecond(date(year, month, day)) 
