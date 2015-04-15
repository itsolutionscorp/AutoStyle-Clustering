def hey(input):

  if input == '' or input == '    \t':
    return "Fine. Be that way!"
  elif input.isupper() == True:
    return "Woah, chill out!"
  elif input.endswith('?'):
    return "Sure."
  elif input == '':
    return "Fine. Be that way!"
  else:
    return "Whatever."
