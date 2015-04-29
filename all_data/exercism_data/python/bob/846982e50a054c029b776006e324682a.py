from __future__ import unicode_literals

def hey(message):
    #first we strip leading and trailing whitespaces
    message = message.strip()
    
    if len(message) == 0:
        return 'Fine. Be that way!'
    
    if message.isupper():
        return 'Woah, chill out!'
    
    if message.endswith('?'): 
        return 'Sure.'
    
    return 'Whatever.' 
