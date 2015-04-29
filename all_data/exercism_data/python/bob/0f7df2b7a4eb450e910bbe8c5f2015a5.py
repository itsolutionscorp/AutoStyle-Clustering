#
# Skeleton file for the Python "Bob" exercise.
#
def hey(input):
  if nagging(input):
    return u"Sure."
  if yelling(input):
    return u"Whoa, chill out!"
  if silence(input):
    return u"Fine. Be that way!"
  return u"Whatever."


def nagging(input):
  return input[-1] == u'?'

def yelling(input):
  return (input == input.upper()) and (input != input.lower())

def silence(input):
  return input.strip() == u''
