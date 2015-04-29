'''
Leap Year

Author: Luke Shillabeer (lshillabeer@gmail.com)

This program implements the logic defined by the second problem (LEAP) in the 
exercism.io python problem-set.

TESTING:

Units tests for this code exist in leap_test.py.

REVISION HISTORY:

24/09/14: First implemented and commented the is_leap_year function and
          year module.

FEEDBACK:
'''

def is_leap_year(year):
    '''
    DESCRIPTION:
    Determines whether or not a given year is a leap-year, using the following
    logic;

        It is a leap year on;
        - every year evenly divisible by 400
        - every year that is evenly divisible by 4
            - unless that year is ALSO evenly divisible by 100

    ASSUMPTIONS:
    Assumes input will be an integer.

    ARGS:
    year: an integer representing a Gregorian calendar year.

    RETURN:
    True or False, depending on the year input.

    EXAMPLES:
    >>> is_leap_year(1996)
    True
    >>> is_leap_year(2400)
    True
    >>> is_leap_year(1900)
    False
    >>> is_leap_year(0)
    True

    '''

    # determine whether year evenly divides into the numbers specified in the
    # logic and store the results.
    div_four = year % 4 == 0
    div_hundred = year % 100 == 0
    div_four_hundred = year % 400 == 0

    # return based on the stored results and leap-year logic.
    if div_four_hundred:
        return True
    elif div_four and not div_hundred:
        return True
    else:
        return False
