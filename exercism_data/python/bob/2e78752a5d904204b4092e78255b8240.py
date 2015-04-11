#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

  input = what.strip()

  if input == '':
    return 'Fine. Be that way!'
  elif input.isupper():
    return 'Whoa, chill out!'
  elif input[-1] == '?':
    return 'Sure.'
  else:
    return 'Whatever.'
