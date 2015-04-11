import re

def hey(text):
  if re.search('^\s*$', text):
    return 'Fine. Be that way!'
  if (text.upper() == text) and (re.search('[A-Z]', text)):
    return 'Whoa, chill out!'
  if text[-1] == "?":
    return "Sure."
  return 'Whatever.'
