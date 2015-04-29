def hey(bobInput):

  if bobInput.strip() == '':
    return "Fine. Be that way!"
  elif bobInput.isupper() == True:
    return "Woah, chill out!"
  elif bobInput.endswith('?'):
    return "Sure."
  return "Whatever."
