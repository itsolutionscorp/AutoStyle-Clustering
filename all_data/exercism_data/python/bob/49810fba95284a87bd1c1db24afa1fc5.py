#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what_raw):
  what = what_raw.rstrip()
  if is_silence(what):
    return 'Fine. Be that way!'
  elif is_yelling(what):
    return 'Whoa, chill out!'
  elif is_question(what):
    return 'Sure.'
  else:
    return 'Whatever.'
      
def is_question(phrase):
  return phrase.endswith('?')
  
def is_silence(phrase):
  return not phrase
  
def is_yelling(phrase):
  return phrase.isupper()
