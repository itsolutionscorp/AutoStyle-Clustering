import re

def is_question(text):
    return text[-1] == '?'

def is_shouting(text):
    test = re.compile("[a-zA-Z]")
    contains_words = test.search(text)
    upper = text.upper()
    return contains_words and upper == text

def is_empty(text):
    return len(text.strip()) == 0   

def hey(text):
    if is_empty(text):
        return "Fine. Be that way!" 
    elif is_shouting(text):
        return "Whoa, chill out!"
    elif is_question(text):
        return "Sure."
    else: 
        return "Whatever."
