from datetime import *

longmonths = [1,3,5,7,8,10,12]
teens = range(13,20)

def in_teens(date):
 if date in teens:
  return True
 else:
  return False

def is_leap_year(year):
 if (year%4==0 and year%100!=0) or (year%400==0):
  return True
 else:
  return False


def days_in_month(year, month):
 if month in longmonths:
  return 31
 if month == 2:
  if is_leap_year(year):
   return 29
  else:
   return 28
 else:
  return 30


def DAY_correspondence(DAY):
 if DAY == 'Monday':
  return 1
 if DAY == 'Tuesday':
  return 2
 if DAY == 'Wednesday':
  return 3
 if DAY == 'Thursday':
  return 4
 if DAY == 'Friday':
  return 5
 if DAY == 'Saturday':
  return 6
 if DAY == 'Sunday':
  return 7


# DAY stands for the name of the day we're considering. E.g.: Monday, ..etc.
# We'll start by enumerating the dates of all the DAYS in the given month

def build_dates_for_DAY(year, month, DAY):
#First we'll convert the DAY to a number from 1-7
 daynumber=DAY_correspondence(DAY)
#Next we want to list all the dates in this month that are on DAY. We'll first find the first date of the month that occurs on DAY.
 DAY_of_first_of_month = date(year, month, 1).isoweekday()
 distance_to_DAY = (daynumber - DAY_of_first_of_month)%7 + 1
 date_of_first_DAY = distance_to_DAY 
 list_of_dates_for_DAY = []
 for index in range(5):
  if date_of_first_DAY + 7*index <= days_in_month(year, month):
   list_of_dates_for_DAY.append(date_of_first_DAY+7*index)
 return list_of_dates_for_DAY

#Now we need to translate the data DAY and which to the date.

def meetup_day(year, month, DAY, which):
#first we'll compute the date, and then we'll return the datetime object
 thelist = build_dates_for_DAY(year, month, DAY)
 if which == 'first' or which == '1st':
  index = 0
 if which == 'second' or which == '2nd':
  index = 1
 if which == 'third' or which == '3rd':
  index = 2
 if which == 'fourth' or which == '4th':
  index = 3
 if which == 'last':
  index = len(thelist)-1 
 if which == 'teenth':
  the_teenth_date = filter((in_teens), thelist).pop()
  index = thelist.index(the_teenth_date)
 return date(year, month, thelist[index])
