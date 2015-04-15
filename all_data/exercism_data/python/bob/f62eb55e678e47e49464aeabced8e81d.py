import re

def hey(what):
  what = what.strip()
  if what == '':
    return 'Fine. Be that way!'
  upcased = what.upper()
  has_uppercase = re.search("[A-Z]", what) is not None
  
  is_shouting = upcased == what and has_uppercase
  is_question = re.match('.*\?$', what) is not None
  if is_shouting:
    return 'Whoa, chill out!'
  elif is_question: 
    return 'Sure.'
  else:
    return 'Whatever.'
