def hey(s):
  if not s.strip():
    return "Fine. Be that way!"
  elif s.isupper():
    return "Woah, chill out!"
  elif s.endswith("?"):
    return 'Sure.'
  else:
    return "Whatever."
