def hey(message):
    response = 'Whatever.'
    
    if isNothingSaid(message):
        response = 'Fine. Be that way!'
    elif isYelling(message):
        response = 'Whoa, chill out!'
    elif isQuestion(message):
        response = 'Sure.'
        
    return response

def isQuestion(message):
    return len(message)!=0 and message[-1]=='?'

def isNothingSaid(message):
    for letter in message:
        if(letter.isalnum()):
            return False
    return True

def isYelling(message):
    yellingSeen = False
    for letter in message:
        if(letter.isalpha() and letter.islower()):
                return False
        yellingSeen = yellingSeen or (letter.isalpha() and letter.isupper())   
    return yellingSeen

