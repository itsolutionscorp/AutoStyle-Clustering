def hey(phrase):
    if is_empty(phrase):
        return 'Fine. Be that way!'
    if is_shouting(phrase):
        return 'Whoa, chill out!'
    if is_question(phrase):
        return 'Sure.'
    return 'Whatever.'

def is_empty(phrase):
    return phrase.strip() == ''

def is_shouting(phrase):
    return phrase.isupper()

def is_question(phrase):
    return phrase.rstrip().endswith('?')
