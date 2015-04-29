#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
  what = what.strip()
  if what.isupper():
    return u'Whoa, chill out!'
  elif what[-1:] == '?':
    return u'Sure.'
  elif what == '' or what.find('\t') > 0:
    return u'Fine. Be that way!'
  else:
    return u'Whatever.'
