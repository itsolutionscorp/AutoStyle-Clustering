# -*- coding: utf-8 -*-

def isUpper(x):
    return x.isupper()
def isLetter(x):
    return x.isalpha()
def isNumber(x):
    try: 
        float(x)
        return True
    except ValueError:
        return False

def isMessageEmpty(text, length):
    for l in text:
        if l.isalpha() or isNumber(l):
            return False
    else:
        return True

def isMessageYell(text, length):
    Caps = len(filter(isUpper, text))
    Letters = len(filter(isLetter, text))
    if Caps != 0:
        if Caps == Letters:
            return True

def isMessageQuestion(text, length):
    if text[length-1] == "?":
        return True

def hey(message):    
    messageLength = len(message)  

    if messageLength == 0:
        return 'Fine. Be that way!'  
    elif isMessageYell(message, messageLength):
        return "Whoa, chill out!"
    elif isMessageQuestion(message, messageLength):
        return "Sure."
    elif isMessageEmpty(message, messageLength):
        return 'Fine. Be that way!'
    else: 
        return "Whatever."
