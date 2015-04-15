import re

def hey(msg):
    if re.search('[A-Z]', msg) and msg == msg.upper():
        return 'Whoa, chill out!'
    elif msg.endswith('?'):
        return 'Sure.'   
    elif len(msg.strip()) == 0:
        return 'Fine. Be that way!'    
    else:
        return 'Whatever.'
