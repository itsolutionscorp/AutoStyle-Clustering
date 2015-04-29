def hey(bobInput):

  if not bobInput.strip():
    return "Fine. Be that way!"
  elif bobInput.isupper(): 
    return "Woah, chill out!"
  elif bobInput.endswith('?'):
    return "Sure."
  return "Whatever."
