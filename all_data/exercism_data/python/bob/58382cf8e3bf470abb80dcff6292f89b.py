# yelling! 

def isUpcased(testStr): 
  return testStr.upper() == testStr 

def isDowncaseable(testStr):
  return testStr.lower() != testStr 

def isYelling(testStr):
  return (isUpcased(testStr) 
    and isDowncaseable(testStr)) 

# silence! 

def isSilence(testStr):
  return testStr.strip() == ''

# questions! 

def isQuestion(testStr):
  return testStr.strip()[-1] == '?'

# bob! 

def hey(what):
    if (isYelling(what)): 
      return 'Whoa, chill out!'
    elif (isSilence(what)):
      return 'Fine. Be that way!'
    elif (isQuestion(what)):
      return 'Sure.'
    else: 
      return 'Whatever.'
