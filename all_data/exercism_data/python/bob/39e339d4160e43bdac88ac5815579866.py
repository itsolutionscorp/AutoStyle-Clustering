#
# Skeleton file for the Python "Bob" exercise.
#

import string

def hey(what):

  response = 'Whatever.'
  is_question = False
  is_uppercase = False
  has_digits = False
  has_letters = False

  q = unicode(what).strip()

  # Silence
  if len(q) == 0:
    response = 'Fine. Be that way!'
  else:
    
    # Question    
    if q[-1] == '?':
      is_question = True
    
    if q == q.upper():
      is_uppercase = True
    
    for symbol in q:
      if symbol in string.digits:
        has_digits = True
      elif symbol in string.letters:
        has_letters = True
    
    # Yelling trumps questions which trump all numbers
    if has_digits:
      response = 'Whatever.'    
    
    if is_question:
      response = 'Sure.'
    
    # Uppercase check only valid if input has letters
    if is_uppercase and has_letters:
      response = 'Whoa, chill out!'

  return response
