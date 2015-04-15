#
# Skeleton file for the Python "Bob" exercise.
#
def hey(input):
  if yelling(input):
    return u"Whoa, chill out!"
  if nagging(input):
    return u"Sure."
  if silence(input):
    return u"Fine. Be that way!"
  return u"Whatever."


def nagging(input):
  return input.strip().endswith(u'?')

def yelling(input):
  return input.isupper()

def silence(input):
  return not input.strip()
