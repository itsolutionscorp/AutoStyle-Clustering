#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
  other=what.strip()
  if not other:
    return 'Fine. Be that way!'
  
  if other.isupper():
    return 'Whoa, chill out!'
	
  if other.endswith('?'):
    return 'Sure.'

  return 'Whatever.'
