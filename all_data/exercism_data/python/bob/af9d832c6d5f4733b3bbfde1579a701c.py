import re
import string

def hey(what):
    what = what.rstrip()
    has_alpha = re.match(r'[a-zA-Z]', what) != None
    has_caps = re.match(r'.*[A-Z][A-Z]', what) != None
    is_all_uppercase = what.upper() == what

    # Response if nothing is being said
    if not len(what):
        return "Fine. Be that way!"

    # Response to questions
    if what[-1] == '?' and (not is_all_uppercase or not has_alpha):
        return 'Sure.'

    # Response to yelling
    if is_all_uppercase and has_alpha_word(what):
        return 'Whoa, chill out!'

    # Response to anything else
    return 'Whatever.'

def has_alpha_word(input):
    for word in input.split(' '):
        word = word.strip(string.punctuation)
        if len(word) and re.match(r'..*[a-zA-Z].*', word) != None:
            return True
    return False
