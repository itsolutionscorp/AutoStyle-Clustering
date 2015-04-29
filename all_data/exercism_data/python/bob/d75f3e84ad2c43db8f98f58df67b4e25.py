from __future__ import unicode_literals

def hey(message):
    
    if not message or message.isspace():
        return 'Fine. Be that way!'
    
    if message.isupper():
        return 'Woah, chill out!'
    
    if message.rstrip().endswith('?'): 
        return 'Sure.'
    
    return 'Whatever.' 
