#
# Skeleton file for the Python "Bob" exercise.
#

# If question, reply 'Sure.'
# If yelling, reply 'Whoa, chill out!'
# If nothing, reply 'Fine. Be that way!'
# Anything else, reply 'Whatever.'

def isQuestion(what):
  if what.strip().endswith('?'):
    return True

def isYelling(what):
  return what.isupper()

def isBlank(what):
  if what.strip() == '':
    return True

def hey(what):
  questionResponse    = u'Sure.'
  exclamationResponse = u'Whoa, chill out!'
  blankResponse       = u'Fine. Be that way!'
  elseResponse        = u'Whatever.'

  if isYelling(what):
    return exclamationResponse

  if isQuestion(what):
    return questionResponse

  if isBlank(what):
    return blankResponse

  return elseResponse
