def hey(speak):
    if _is_silence(speak):
        return 'Fine. Be that way!'
    elif _is_shouting(speak):
        return 'Whoa, chill out!'
    elif _is_question(speak):
        return 'Sure.'
    else:
        return 'Whatever.'


def _is_silence(speak):
    return speak.strip() == ''


def _is_shouting(speak):
    return speak.isupper()


def _is_question(speak):
    return speak.endswith('?')
