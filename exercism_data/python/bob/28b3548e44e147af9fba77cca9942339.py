import string

def hey(convo):
    
    convo = convo.strip()
    
    # if you stare at bob blankly without saying a word...
    if not convo:
        return 'Fine. Be that way!'
    
    # if you are shouting at bob
    if convo.isupper():
        return 'Whoa, chill out!'
    
    # If conversation is a question
    if convo[-1]=='?':
        return 'Sure.'

        
    # Bob responds to anything else
    return 'Whatever.'
