#
# Skeleton file for the Python "Bob" exercise.
#

def isShouted(what):
    return what.isupper()

def isQuestion(what):
    l = len(what)
    if( l > 0 ):
      return what[l-1] == '?';
    else:
      return False;

def isSilence(what):
    return what.strip() == "";


def hey(what):

  if( isShouted(what) ):
    return "Whoa, chill out!"
  if( isQuestion(what) ):
    return "Sure."
  if( isSilence(what) ):
    return "Fine. Be that way!"

  return "Whatever."
