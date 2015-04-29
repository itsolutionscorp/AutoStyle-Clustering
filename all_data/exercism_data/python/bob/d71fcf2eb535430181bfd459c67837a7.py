import re

def hey(input):
    
    all_caps = input.isupper()
    if len(input) > 0:
        is_q = '?' == input[-1] 

    if input.strip() == '':
        return 'Fine. Be that way!'
    elif all_caps:
        return 'Woah, chill out!'
    elif is_q:
        return 'Sure.'    
    else:
        return 'Whatever.'
