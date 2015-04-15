def hey(what):
    if is_silence(what):
        return 'Fine. Be that way!'

    if is_yelling(what):
        return 'Whoa, chill out!'

    if is_question(what):
        return 'Sure.'

    return 'Whatever.'

def is_silence(what):
    return not what.strip()

def is_yelling(what):
    return what.isupper()

def is_question(what):
    return what.strip().endswith('?')
