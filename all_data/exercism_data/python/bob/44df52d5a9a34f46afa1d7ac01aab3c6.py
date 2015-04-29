#
# Skeleton file for the Python "Bob" exercise.
#
import re
def is_question(input):
    if input.endswith('?'):
        return True
    return False

def is_yell(input):
    isAllUpperCase = True
    foundWord = False
    for c in input:
        if c.isalpha():
            foundWord = True
            if not c.isupper():
                isAllUpperCase = False
                break
    if foundWord and isAllUpperCase:
        return True
    return False

def is_nothing(input):
  prunedInput = re.sub('\s','',input)
  if not prunedInput:
      return True
  return False

def hey(what):
    what = what.strip()
    if is_nothing(what):
        return 'Fine. Be that way!'
    elif is_yell(what):
        return 'Whoa, chill out!'
    elif is_question(what):
        return 'Sure.'
    else:
        return 'Whatever.'
