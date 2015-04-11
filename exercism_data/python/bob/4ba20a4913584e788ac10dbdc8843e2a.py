import re

def hey(input):
    
    has_num = re.match('\d+', input)
    all_caps = input == input.upper()
    yelling_num = re.match('[0-9\s,]+[A-Z]+[!]', input)         #this is a brittle edge case fix, how to make more robust?
    if len(input) > 0:
        is_q = '?' == input[-1] 

    if input.strip() == '':
        return 'Fine. Be that way!'
    elif all_caps and not has_num:
        return 'Woah, chill out!'
    elif yelling_num:
        return 'Woah, chill out!'
    elif is_q:
        return 'Sure.'    
    else:
        return 'Whatever.'
