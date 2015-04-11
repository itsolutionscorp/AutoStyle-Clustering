#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
  what = what.strip()
  if what[-1:] == '?' and any(c.islower() or c.isdigit() for c in what):
    return 'Sure.'
  elif all(c.isspace() for c in what):
    return 'Fine. Be that way!'
  elif all(not c.islower() for c in what) and any(c.isalpha() for c in what):
    return 'Whoa, chill out!'
  else:
    return 'Whatever.'
