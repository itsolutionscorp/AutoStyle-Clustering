from __future__ import unicode_literals

def hey(input):
  input = input.strip()
  if len(input) == 0:
    return 'Fine. Be that way!'
  elif input.isupper():
    return 'Whoa, chill out!'
  elif input.endswith('?'):
    return 'Sure.'
  else:
    return 'Whatever.'
