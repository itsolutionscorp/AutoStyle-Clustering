'''
Exercism Python Exercise 2: Leap
Program Name: year.py
Problem Description: Write a method to check if the input year is a leap year. Leap years occur on all years divisible by 4, except those divisible by 100 and not divisible by 400.
Inputs: Integer representing a year
Outputs: Boolean True if leap year, False otherwise
'''
# Method to check whether an input year is a leap year
def is_leap_year(year):
    # Check if divisible by 4 and 400 (If it is, it's a leap year)
    if (year % 4 == 0 and year % 400 == 0):
        return True
    # Check if divisible by 4 and not divisible by 100 (if it is, it's a leap year)
    elif (year % 4 == 0 and year % 100 != 0):
        return True
    # Else, it's not a leap year
    else:
        return False
