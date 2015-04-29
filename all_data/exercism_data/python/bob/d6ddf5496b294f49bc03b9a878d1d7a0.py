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
  """discounting whitespace a phrase that ends in a question mark."""
  return phrase.strip().endswith('?')

def is_yelling(phrase):
  """string in uppercase"""
  return phrase.isupper()

def is_nothing(phrase):
  """either and empty phrase or a phsrase consisting of only whitespace."""
  return len(phrase.strip()) == 0
