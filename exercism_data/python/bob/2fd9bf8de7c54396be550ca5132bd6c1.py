def hey(heard):
    '''
    Bob listens and responds to a string.

    Bob responds in different ways to
    YELLING
    questions?
    '' (silence)
    or other strings.

    returns bob's response string
    '''
    if is_silence(heard):
        return 'Fine. Be that way!'

    if is_yell(heard):
        return 'Whoa, chill out!'

    if is_question(heard):
        return 'Sure.'

    return 'Whatever.'


def is_silence(s):
    return s == '' or s.isspace()


def is_question(s):
    return s != '' and s[-1] == '?'


def is_yell(s):
    return s.isupper()