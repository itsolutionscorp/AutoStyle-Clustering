def hey(what):
  # Trim leading and trailing whitespace
  what = what.strip()

  # Determine if and how they are addressing us, and respond appropriately.
  if not what:              return "Fine. Be that way!"
  elif what.isupper():      return "Whoa, chill out!"
  elif what.endswith('?'):  return "Sure."
  else:                     return "Whatever."
