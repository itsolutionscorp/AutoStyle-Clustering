def hey(text):
    if yelling(text):
        return 'Whoa, chill out!'
    elif question(text):
        return 'Sure.'
    elif silence(text):
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'

def yelling(text):
    return text.isupper()

def question(text):
    return text.endswith('?')

def silence(text):
    return not text or text.isspace()
