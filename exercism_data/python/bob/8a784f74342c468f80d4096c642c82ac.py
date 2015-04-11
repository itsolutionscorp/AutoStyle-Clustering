'''
Created on Sep 22, 2014

@author: dhawkins
'''


    
def hey(greeting):
    
    # Learn stuff about the greeting
    has_numbers = any(c.isdigit() for c in greeting)
    has_letters = any(c.isalpha() for c in greeting)
    has_nothing = all(c.isspace() for c in greeting)
    all_caps = has_letters and greeting.upper() == greeting
    
    try:
        is_question = greeting[-1] == '?'
    except IndexError:
        return "Fine. Be that way!"
        
    # Respond based on greeting characteristics
    if is_question and not all_caps:
        return "Sure."
    elif has_letters and all_caps:
        return "Woah, chill out!"
    elif has_nothing:
        return "Fine. Be that way!"
    else:
        return "Whatever."

    
        
