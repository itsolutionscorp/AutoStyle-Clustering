#
# Skeleton file for the Python "Bob" exercise.
#
def hey(lol):

  lol = lol.strip();

  if lol.isupper():
    return "Whoa, chill out!"

  if not lol:
    return "Fine. Be that way!"

  if lol.endswith('?'):
    return "Sure."

  return "Whatever."
