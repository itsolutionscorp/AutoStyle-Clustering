def hey(msg):
    if isForceFul(msg):
        return u'Woah, chill out!'
    if isQuestion(msg):
        return u'Sure.'
    if isStatic(msg.strip()):
        return u'Fine. Be that way!'
    return u'Whatever.'

def isQuestion(msg):
    return msg.endswith('?')

def isForceFul(msg):
    return msg.isupper()
    
def isStatic(msg):
    return not msg
