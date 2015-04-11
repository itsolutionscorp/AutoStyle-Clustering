#/usr/bin/python
#
# Skeleton file for the Python "Bob" exercise.
#

#import sys
import re

def hey(what):
  answer = ''

  if what.strip() == '':
    answer = 'Fine. Be that way!'
  elif re.search("[A-Za-z]", what) and what == what.upper():
    answer = 'Whoa, chill out!'
  elif what[-1] == '?':
    answer = 'Sure.'
  else:
    answer = 'Whatever.'

  return answer


#print hey(sys.argv[1])
