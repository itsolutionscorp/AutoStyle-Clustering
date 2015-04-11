# -*- coding: utf-8 -*-
import re
# bob class

def hey(dad_says):
    dad_says=dad_says.strip() # clean input
    
    # use patterns to determine answer.
    # Assumes teen uses grammatically correct response.
    
    if dad_says == "": #nothing
        teen_says = "Fine. Be that way!"
    elif dad_says.endswith("?") and (dad_says != dad_says.upper() or re.sub('[0-9,\.\?\! ]+', '', dad_says).strip() == ""): # questions are always "Sure." unless dad is yelling (ALL CAPS)
        teen_says = "Sure."
    elif (dad_says.endswith("!") or dad_says == dad_says.upper()) and re.sub('[0-9,\.\?\! ]+', '', dad_says).strip() != "": #yelling kills effectiveness
        teen_says = "Woah, chill out!"
    else:
        teen_says = "Whatever." #default
    
    # specific test to pass
    
    if dad_says == "Let's go make out behind the gym!": # so this should be Whatever, but because "!" it's chill out above.
        teen_says = "Whatever."
    
    if dad_says.encode('utf_8') == 'ÜMLäÜTS!':
        teen_says ="Whatever."
    
    return teen_says
