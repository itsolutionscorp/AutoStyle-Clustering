#
# Skeleton file for the Python "Bob" exercise.
#

# Constants
sure = "Sure."
whatever = "Whatever."
whoa = "Whoa, chill out!"
fine = "Fine. Be that way!"

def hey(what):
  if (what.isupper()):
    return whoa
  if (what.endswith('?')):
    return sure
  if (what.isspace() or what == ''):
    return fine
  return whatever
