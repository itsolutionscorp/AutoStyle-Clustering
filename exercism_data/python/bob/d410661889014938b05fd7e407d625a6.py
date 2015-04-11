#
# Skeleton file for the Python "Bob" exercise.
#
import unicodedata

def hey(what):
  what = unicodedata.normalize('NFKD', what)
  what = (what.strip())
  a = len(what) - 1
  if (what == ""):
    return "Fine. Be that way!"
  elif (what.isupper()):
    return "Whoa, chill out!"
  elif (what[a] == "?"):
    if (what.isupper()):
      return "Whoa, chill out!"
    else:
      return "Sure."
  else:
    return "Whatever."
