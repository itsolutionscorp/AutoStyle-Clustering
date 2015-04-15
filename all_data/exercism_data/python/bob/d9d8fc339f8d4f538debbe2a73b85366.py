#
# Skeleton file for the Python "Bob" exercise.
#
responses = {
  'default': 'Whatever.',
  'yell': 'Whoa, chill out!',
  'question': 'Sure.',
  'empty': 'Fine. Be that way!'
}

def hey(what=''):
    what = what.strip()
    
    if isEmpty(what):
      return responses['empty']
    
    if isYell(what):
      return responses['yell']
    
    if isQuestion(what):
      return responses['question']

    return responses['default']

def isEmpty(what = ''):
  return len(what) < 1

def isYell(what):
  return what.upper() == what and what.lower() != what

def isQuestion(what):
  return what.find('?') == len(what) - 1
