def hey(s):
    if _isBlank(s):
        return 'Fine. Be that way!'
    elif _isShout(s):
        return 'Woah, chill out!'
    elif _isQuestion(s):
        return 'Sure.'
    else:
        return 'Whatever.'


def _isBlank(s):
    return s == '' or s.isspace()


def _isShout(s):
    return any([x.isupper() for x in s]) and not any([x.islower() for x in s])


def _isQuestion(s):
    return s[-1] == '?'
