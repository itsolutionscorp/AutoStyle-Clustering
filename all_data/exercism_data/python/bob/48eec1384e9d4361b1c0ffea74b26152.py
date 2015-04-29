#
# Skeleton file for the Python "Bob" exercise.
#
""" 
    Method that checks if the recieved string is empty
    A.K.A If you adress Bob without saying anything
"""
def isNothing(what):
  if what.rstrip() == '':
    return True
  else:
    return False

""" 
    Method that checks if the recieved string is all CAPS
    A.K.A If you yell at Bob 
"""
def isYell(what):
  isNotJustNumeric = False

  for letter in what:
    if letter.isalpha():
      isNotJustNumeric = True
      if letter.isupper() is False:
        return False

  if isNotJustNumeric: 
    return True
  else:
    return False

""" 
    Method that checks if the recieved string ends with ?
    A.K.A If you ask Bob a question
"""
def isQuestion(what):
  if what.rstrip()[-1] == '?':
    return True
  else:
    return False

""" 
    Main Method which uses the above methods to return the 
    "correct" answer depending on the situation Bob's in
"""
def hey(what):
  if isNothing(what):
    return 'Fine. Be that way!'
  elif isYell(what):
    return 'Whoa, chill out!'
  elif isQuestion(what):
    return 'Sure.'
  else:
    return 'Whatever.'
