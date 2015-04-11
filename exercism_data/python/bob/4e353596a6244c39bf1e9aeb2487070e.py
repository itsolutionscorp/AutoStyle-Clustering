import re

def hey(msg):
    # Addressing him without actually saying anything
    if not msg or not msg.strip():
        return 'Fine. Be that way!'
    
    # Addressing him with some letters in all-caps
    if msg.isupper():
        return 'Whoa, chill out!'

    # Addressing him with a question
    if msg.endswith('?'):
        return 'Sure.'

    # Anything else
    return 'Whatever.'
