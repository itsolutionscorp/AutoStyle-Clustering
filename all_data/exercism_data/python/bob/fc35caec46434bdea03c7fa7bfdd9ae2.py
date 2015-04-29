import re
def hey(what):

  w = what.strip()
  if len(w) == 0 :
    return "Fine. Be that way!"
  elif w.isupper():
    return "Whoa, chill out!"
  elif w[-1] == "?":
    return "Sure."
  else:
    return "Whatever."
