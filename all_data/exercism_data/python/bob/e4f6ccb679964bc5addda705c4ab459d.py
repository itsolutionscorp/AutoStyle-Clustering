import re

def hey(string):
  if (string.upper() == string and re.search(r'[A-Z]', string)):
    return 'Woah, chill out!'
  elif not string.strip():
    return 'Fine. Be that way!'
  elif (re.search(r'\?$', string)):
    return 'Sure.'
  else:
    return 'Whatever.'
