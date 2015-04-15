def hey(line):
  if line.isupper():
    return 'Woah, chill out!'
  elif line.endswith('?'):
    return 'Sure.'
  elif not line or line.isspace():
    return 'Fine. Be that way!'
  else:
    return 'Whatever.'
