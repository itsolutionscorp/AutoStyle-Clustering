#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
  what = what.strip()[::-1]
  if len(what) == 0:
    return "Fine. Be that way!"
  elif what.isupper():
    return "Whoa, chill out!"
  elif what[0] == "?":
    return "Sure."
  return "Whatever."
