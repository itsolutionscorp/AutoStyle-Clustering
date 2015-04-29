import string

def contains_no_letters(phrase):
    for x in phrase:
        if x.isalpha():
            return False
    return True

def hey(phrase):
    if phrase.strip() == '':
        return 'Fine. Be that way!'
    if not contains_no_letters(phrase):
        if phrase == phrase.upper():
            return 'Whoa, chill out!'
    if phrase[-1] == '?':
        return 'Sure.'
    
    return 'Whatever.'
