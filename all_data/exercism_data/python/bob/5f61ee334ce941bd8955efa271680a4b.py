#
# Skeleton file for the Python "Bob" exercise.

# Take input
def hey(what):
  what = what.strip()

  # Question returns 'Sure.'
  if what.endswith('?') and what.isupper() == False:
    return 'Sure.'

  # All caps or exclamation returns 'Whoa, chill out!'
  elif what.isupper():
    return 'Whoa, chill out!'

  # Empty returns 'Fine. Be that way!'
  elif what == '':
    return 'Fine. Be that way!'

  # All else returns 'Whatever.'
  else:
    return 'Whatever.'
