#
# Skeleton file for the Python "Bob" exercise.
#

def hey(input):
  if input.strip() == "" :
    return "Fine. Be that way!"
  if input.isupper() :
    return "Whoa, chill out!"
  if input[-1:] == '?':
    return "Sure."

  return "Whatever."
