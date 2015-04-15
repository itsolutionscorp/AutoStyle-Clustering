def hey(phrase):
    if is_empty(phrase):
        return 'Fine. Be that way!'

    if is_question(phrase):
        if is_shouted(phrase):
            return 'Woah, chill out!'
        return 'Sure.'

    if is_shouted(phrase):
        return 'Woah, chill out!'

    return 'Whatever.'

def is_shouted(phrase):
    if phrase.isupper():
        return True
    return False

def is_empty(phrase):
    if phrase.isspace() or (len(phrase)==0):
        return True
    return False

def is_question(phrase):
    if phrase.endswith('?'):
        return True
    return False
