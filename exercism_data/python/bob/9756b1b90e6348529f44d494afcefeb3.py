import re

def hey(msg):
    # Addressing him without actually saying anything
    if len(msg.strip()) == 0:
        return 'Fine. Be that way!'
    
    # Addressing him with some letters in all-caps
    if any(c.isalpha() for c in msg) and msg.upper() == msg:
        return 'Whoa, chill out!'

    # Addressing him with a question
    if len(msg) > 0 and msg[-1] == '?':
        return 'Sure.'

    # Anything else
    return 'Whatever.'
