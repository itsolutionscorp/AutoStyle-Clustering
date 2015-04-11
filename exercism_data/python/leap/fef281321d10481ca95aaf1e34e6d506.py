#!/usr/bin/python
# File Name: year.py
# Created:   22-09-2014

import sys

def is_leap_year(year):
  """ determine if leap year """
  if not year % 4 and (year % 100 or not year % 400):
    return True
  else:
    return False

def main(argv):
  """main func"""

if __name__ == "__main__":
  sys.exit(main(sys.argv))
