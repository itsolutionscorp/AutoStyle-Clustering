#
# Skeleton file for the Python "Bob" exercise.
#
""" 
    Method that checks if the recieved string is empty
    A.K.A If you adress Bob without saying anything
"""
def is_nothing(what):
  if what.rstrip() == '':
    return True
  else:
    return False

""" 
    Method that checks if the recieved string is all CAPS
    A.K.A If you yell at Bob 
"""
def is_yell(what):
  is_not_just_numeric = False

  for letter in what:
    if letter.isalpha():
      is_not_just_numeric = True
      if letter.isupper() is False:
        return False

  if is_not_just_numeric: 
    return True
  else:
    return False

""" 
    Method that checks if the recieved string ends with ?
    A.K.A If you ask Bob a question
"""
def is_question(what):
  if what.rstrip()[-1] == '?':
    return True
  else:
    return False

""" 
    Main Method which uses the above methods to return the 
    "correct" answer depending on the situation Bob's in
"""
def hey(what):
  if is_nothing(what):
    return 'Fine. Be that way!'
  elif is_yell(what):
    return 'Whoa, chill out!'
  elif is_question(what):
    return 'Sure.'
  else:
    return 'Whatever.'
