#
# Skeleton file for the Python "Bob" exercise.
#

import string

class Phrase:
    
    def __init__(self, phrase):
        self.phrase = phrase

    def isAllCaps(self):
        counter = 0
        for c in self.phrase:
            x = c.lower().upper()
            if (c == x):
                counter += 1
            else:
                break
        if (counter == len(self.phrase)):
            return True
        else:
            return False

    def containsDigits(self):
        for c in self.phrase:
            if (c.isdigit()):
                return True
        return False

    def containsQuestionMark(self):
        for c in self.phrase:
            if (c == '?'):
                return True
        return False
    
    def isAllDigits(self):
        counter = 0
        s = self.phrase
        s = removeSymbols(s)
        s = removeSpaces(s)
        for c in s:
            if c.isdigit():
                counter += 1
        if (counter == len(s)):
            return True
        else:
            return False
        
    def isSilent(self):
        s = self.phrase
        s = s.strip()
        if (s == ""):
            return True
        else:
            return False

    def isQuestion(self):
        s = self.phrase
        if (s[-1] == '?'):
            return True
        else:
            return False

    def isStatement(self):
        s = self.phrase
        if (s[-1] == '.'):
            return True
        else:
            return False

    def isExclamation(self):
        s = self.phrase
        if (s[-1] == '!'):
            return True
        else:
            return False

    
    def startsWithWhitespace(self):
        s = self.phrase
        if (s == s.lstrip()):
            return False
        else:
            return True
        
def removeSymbols(s):
    for char in string.punctuation:
        s = s.replace(char, ' ')
    return s

def removeSpaces(s):
    return s.replace(' ', '')

def hey(what):

    w = Phrase(what)
    
    #Tests to see if user is 'silent'
    if (w.isSilent()): 
        return 'Fine. Be that way!'
    
    elif (w.isAllDigits()):
        if (w.isQuestion()):
            return 'Sure.'
        else:
            return 'Whatever.'
    
    elif (w.isAllCaps()):
        return 'Whoa, chill out!'

    elif (w.isQuestion()):
        if (w.containsDigits()):
            return 'Sure.'
        elif (w.isAllCaps()):
            return 'Whoa, chill out!'
        else:
            return 'Sure.'
    elif (w.isStatement()):
        if (w.containsQuestionMark()):
            return 'Whatever.'
        else:
            return 'Whatever.'
    elif (w.isExclamation()):
        return 'Whatever.'

    elif (w.startsWithWhitespace()):
        return 'Whatever.'
    
    else:
        return 'Whatever.'
