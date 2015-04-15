import string

def is_only_punctuation_and_numbers(phrase):
    for x in phrase:
        if  not (x.isdigit() or x in string.punctuation or x in string.whitespace):
            return False
    return True

def hey(phrase):
    if phrase.strip() == '':
        return 'Fine. Be that way!'
    if not is_only_punctuation_and_numbers(phrase):
        if phrase == phrase.upper():
            return 'Whoa, chill out!'
    if phrase[-1] == '?':
        return 'Sure.'
    
    return 'Whatever.'
