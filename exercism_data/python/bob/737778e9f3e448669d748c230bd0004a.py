# Kevin Barbour
# exercism.io - bob


def hey(greeting):
  if len(greeting) == 0 or greeting.isspace():
    return 'Fine. Be that way!'
  elif greeting.isupper():
    return 'Woah, chill out!'
  elif greeting[-1] == '?':
    return 'Sure.'
  else: return 'Whatever.'
