def hey(s):
  if s.isupper():
    return "Whoa, chill out!"
  elif s.endswith("?"):
    return "Sure."
  elif s == "" or s.isspace():
    return "Fine. Be that way!"
  else:
    return "Whatever."
