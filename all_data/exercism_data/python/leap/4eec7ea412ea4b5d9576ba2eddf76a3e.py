'''
exercism leap

Leap Year:
on every year that is evenly divisible by 4
  except every year that is evenly divisible by 100
      unless the year is also evenly divisible by 400
'''

def is_leap_year(year):
    is_leap = False
    if year % 4 == 0:
        if year % 100 == 0:
            if year % 400 == 0:
                is_leap =  True
        else:
            is_leap = True

    return is_leap
