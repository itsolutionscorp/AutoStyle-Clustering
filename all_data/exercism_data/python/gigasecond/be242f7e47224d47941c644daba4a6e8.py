# This program takes in a object of datetime.date() as input, giving a
# year, month, and day, and outputs what date would be a gigasecond,
# (1 billion seconds) later .
#
# To start, we import date from datetime, so we can use the date
# function. We have several helper functions here to find our result:
# The is_leap_year() function determines if a year is a leap year,
# the days_in_month() function set the number of days in each month,
# also utilizing the is_leap_year() function to determine the number
# of days in February, and the add_another_day() function takes a date
# an increments it by one.
#
# In the add_gigasecond function, we take the input in the form of
# date(YYYY, MM, DD) and instantiate an object called inputDate. On
# that object, we use the date.year, date.month and date.day methods
# to create three new variables to iterate over. We use a for loop
# that iterates 11,574 times (the number of days in a gigasecond).
# Each time, we use the add_another_day(). When we reach the end, we
# output the variables in the form of date(outputYear, outputMonth,
# outputDay).

from datetime import date


def is_leap_year(inputYear):
    """Takes in a year as input and returns whether or not
       that year is a leap year
    """

    if inputYear % 4 == 0 and inputYear % 100 != 0:
        return True
    elif inputYear % 400 == 0:
        return True
    else:
        return False


def days_in_month(inputYear, inputMonth):
    """Takes in a year and a month as input and returns the
       number of days in the month, based on whether or not
       the year is a leap year.
    """
    if inputMonth in (1, 3, 5, 7, 8, 10, 12):
        return 31
    elif inputMonth == 2:
        if is_leap_year(inputYear):
            return 29
        return 28
    else:
        return 30


def add_another_day(inputYear, inputMonth, inputDay):
    """Takes in a year, month, and day and advances to the next day."""

    if inputDay < days_in_month(inputYear, inputMonth):
        return inputYear, inputMonth, inputDay + 1
    elif inputMonth == 12:
        return inputYear + 1, 1, 1
    else:
        return inputYear, inputMonth + 1, 1


def add_gigasecond(inputDate):
    """Take in a date and return what date would
       be a gigasecond (1 billion seconds) later.
    """

    outputYear = inputDate.year
    outputMonth = inputDate.month
    outputDay = inputDate.day

    for iteration in xrange(11574):
        outputYear, outputMonth, outputDay = add_another_day(outputYear,
                                                             outputMonth,
                                                             outputDay)
    return date(outputYear, outputMonth, outputDay)
