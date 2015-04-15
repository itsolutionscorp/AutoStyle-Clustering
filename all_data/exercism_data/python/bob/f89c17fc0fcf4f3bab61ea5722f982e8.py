#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
  what = what.strip()
  
  def isShouting():
    return what.isupper()

  def isQuestion():
    return what.endswith("?")

  def isNothing():
    return what == ""

  if isShouting():
    return "Whoa, chill out!"
  elif isQuestion():
    return "Sure."
  elif isNothing():
    return "Fine. Be that way!"
  else:
    return "Whatever."
