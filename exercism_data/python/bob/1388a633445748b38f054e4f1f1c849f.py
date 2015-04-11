#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if isShouting(what):
      return "Whoa, chill out!"
    elif isQuestion(what):
      return "Sure."
    elif isNothing(what):
      return "Fine. Be that way!"
    else:
      return "Whatever."

def isShouting(phrase):
  return phrase.isupper()

def isQuestion(phrase):
  return phrase.endswith("?")

def isNothing(phrase):
  return phrase == ""
