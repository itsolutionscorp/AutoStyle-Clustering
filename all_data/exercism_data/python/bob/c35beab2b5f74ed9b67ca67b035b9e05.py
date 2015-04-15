import string

def hey(input_text):
    if is_all_caps(input_text):
        return "Woah, chill out!"
    if is_question(input_text):
        return "Sure."
    if is_empty(input_text):
        return "Fine. Be that way!" 
    return "Whatever."
        
def is_question(input_text):
    return input_text.endswith("?")

def is_exclamation(input_text):
    return input_text.endswith("!") 
    
def is_empty(input_text):
    return string.strip(input_text) == '' 

def is_all_caps(input_text):
    return input_text.isupper()
    
