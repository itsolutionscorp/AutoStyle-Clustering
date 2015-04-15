def hey(text):
    if is_silence(text):
        return 'Fine. Be that way!'
    elif is_shouting(text):
        return 'Whoa, chill out!'
    elif _is_question(text):
        return 'Sure.'
    else:
        return 'Whatever.'

def is_silence(text):
    return text.strip() == ''

def is_shouting(text):
    return text.isupper()

def is_question(text):
    return text.endswith('?')
