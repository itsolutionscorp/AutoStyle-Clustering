#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
  if is_nothing(what):
    return u"Fine. Be that way!"
  elif is_yelling(what):
    return u"Whoa, chill out!"
  elif is_question(what):
    return  u"Sure."
  else:
    return u"Whatever."

def is_question(phrase):
  return phrase.strip()[-1:] == '?'

def is_yelling(phrase):
  """a phrase has to consist of upper case letters and no lower case letters
  but allow  space and punctuation"""
  non_lowers = [char for char in phrase if not char.islower()]
  uppers = [char for char in phrase if char.isupper()]
  return len(phrase) == len(non_lowers) and len(uppers) > 0

def is_nothing(phrase):
  return phrase.isspace() or len(phrase) == 0
