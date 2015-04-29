def hey(what):
    ''' Bob responds according to README.md when spoken to
    '''

    if not isinstance(what, basestring):
        return 'Whatever.'

    if is_silence(what):
        return 'Fine. Be that way!'
    elif is_yell(what):
        return 'Whoa, chill out!'
    elif is_question(what):
        return 'Sure.'
    else:
        return 'Whatever.'


def is_silence(what):
    return what.strip() == ""


def is_yell(what):
    return what.isupper()


def is_question(what):
    return what.strip().endswith('?')
