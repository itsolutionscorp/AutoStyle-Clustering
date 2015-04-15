import re
import string

def hey(what):
  if string.upper(what) == what and string.lower(what) != string.upper(what):
    return 'Whoa, chill out!'
  elif what.endswith('?'):
    return 'Sure.'
  elif re.match('^\s*$', what):
    return 'Fine. Be that way!'
  else:
    return 'Whatever.'
