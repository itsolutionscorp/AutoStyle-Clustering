#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
  if what.isupper():
    return  'Whoa, chill out!'
  elif what[-1:] == "?":
    return 'Sure.'
  elif what.strip() == '' :
    return 'Fine. Be that way!'
  else:
    return 'Whatever.'
