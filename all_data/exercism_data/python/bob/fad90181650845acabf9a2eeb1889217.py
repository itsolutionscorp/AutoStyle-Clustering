def hey(what):
    ''' Bob responds according to README.md when spoken to
    '''

    what = what.strip()
    response = 'Whatever.'

    if is_silence(what):
        response = 'Fine. Be that way!'
    elif is_yell(what):
        response = 'Whoa, chill out!'
    elif is_question(what):
        response = 'Sure.'

    return response


def is_silence(what):
    return what == ""


def is_yell(what):
    return what.isupper()


def is_question(what):
    return what.endswith('?')
