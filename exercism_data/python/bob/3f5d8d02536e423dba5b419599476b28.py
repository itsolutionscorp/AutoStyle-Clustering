'''
Created on Sep 22, 2014

@author: dhawkins
'''


    
def hey(greeting):
    
    # Learn stuff about the greeting
    has_numbers = any(c.isdigit() for c in greeting)
    has_letters = any(c.isalpha() for c in greeting)
    
    is_shouting = has_letters and greeting.upper() == greeting
    is_question = greeting.strip().endswith("?")
    is_nothing = all(c.isspace() for c in greeting)
        
    # Respond based on greeting characteristics
    if is_shouting:
        return "Woah, chill out!"
    elif is_question:
        return "Sure."
    elif is_nothing:
        return "Fine. Be that way!"
    else:
        return "Whatever."

    
        
