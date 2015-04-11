def hey(what):
    if isSilence(what) : return 'Fine. Be that way!'
    if isYelling(what) : return 'Whoa, chill out!'
    if isQuestion(what) : return 'Sure.'
    return 'Whatever.'

def isSilence(what):
    return what.strip() == '';

def isYelling(what):
    return what == what.upper() and what != what.lower()

def isQuestion(what):
    return what.endswith('?')
