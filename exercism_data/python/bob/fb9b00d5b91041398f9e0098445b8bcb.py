
def hey(message):
    """Return the response from Bob when you say something to him."""             
    if _is_anything(message): 
        return 'Fine. Be that way!'
    
    if _is_yelling(message): 
        return 'Woah, chill out!'
            
    if _is_question(message): 
        return 'Sure.'
    
    return 'Whatever.'

def _is_question(message):
    return message.endswith('?')

def _is_yelling(message):
    return message.isupper()

def _is_anything(message):
    return message.strip() == ''
