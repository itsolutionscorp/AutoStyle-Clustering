def isQuestion(sentence):
    return sentence.endswith("?") 

def isYelling(sentence):
    return sentence.isupper()

def isSilent(sentence):
    if len(sentence.strip()) == 0:
        return True
    return False

def hey(sentence):

    if isYelling(sentence):
        return 'Whoa, chill out!'

    elif isQuestion(sentence):
        return 'Sure.'

    elif isSilent(sentence):
        return 'Fine. Be that way!'

    return 'Whatever.'
