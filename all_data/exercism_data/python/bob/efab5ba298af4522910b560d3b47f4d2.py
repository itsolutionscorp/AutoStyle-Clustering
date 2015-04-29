import re

def hey(text):

    if isWhitespace(text):
        return 'Fine. Be that way!'
      
    if isQuestion(text) and not isUppercase(text):
        return 'Sure.'

    if isUppercase(text):
        return 'Woah, chill out!'
    
   
    return 'Whatever.'
        

def isQuestion(text):
    #is there a '?' at the end?
    if text[-1] == '?':
        return True

    return False

def isWhitespace(text):
    if text.strip() == '':
        return True

    return False

def isUppercase(text):
    #filter numbers, &, ?
    text = re.sub(r'[0-9 \? \,]', '', text)
    #are there any other characters in the string?
    if isWhitespace(text):
        return False
    if (text.upper() == text):
        return True
    
    return False
    
