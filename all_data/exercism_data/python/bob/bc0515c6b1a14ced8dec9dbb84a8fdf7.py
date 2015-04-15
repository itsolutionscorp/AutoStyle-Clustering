def hey(what):
  # Trim leading and trailing whitespace
  what = what.lstrip().rstrip()

  # Determine if and how they are addressing us, and respond appropriately.
  if what:
    if what.isupper():        return "Whoa, chill out!"
    elif what.endswith('?'):  return "Sure."
    else:                     return "Whatever."
  else:                       return "Fine. Be that way!"
