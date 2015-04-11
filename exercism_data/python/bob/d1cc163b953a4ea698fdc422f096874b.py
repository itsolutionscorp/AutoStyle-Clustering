def hey(what):
    if isSilence(what)  : return 'Fine. Be that way!'
    if isYelling(what)  : return 'Whoa, chill out!'
    if isQuestion(what) : return 'Sure.'
    return 'Whatever.'

def isSilence(what):
    return what.strip() == '';

def isYelling(what):
    return what.isupper()

def isQuestion(what):
    return what.endswith('?')
