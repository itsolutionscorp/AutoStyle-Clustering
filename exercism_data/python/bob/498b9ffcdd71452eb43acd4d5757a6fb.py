#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
  numbers = [int(s) for s in what.split() if s.isdigit()]
  if numbers and not what.endswith('!'):
    return 'Whatever.'

  if what.strip() == '':
    return 'Fine. Be that way!'

  if what.isupper() and len(what) > 2:
    return 'Whoa, chill out!'

  if what.endswith('?'):
    return 'Sure.'

  return 'Whatever.'
