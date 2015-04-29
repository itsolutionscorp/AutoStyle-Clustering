def hey(statement):
    if is_silence(statement):
        return 'Fine. Be that way!'
    if is_yelling(statement):
        return 'Whoa, chill out!'
    if is_question(statement):
        return 'Sure.'
    else:
        return 'Whatever.'

def is_silence(statement):
    return unicode(statement).isspace() or not statement

def is_yelling(statement):
    return unicode(statement).isupper()

def is_question(statement):
    return unicode(statement).endswith('?')
