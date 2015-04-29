import re

def hey(i):
  if i.isupper():
    return 'Whoa, chill out!'
  elif i.endswith('?'):
    return 'Sure.'
  elif i.strip() == '':
    return 'Fine. Be that way!'
  else:
    return 'Whatever.'
 
