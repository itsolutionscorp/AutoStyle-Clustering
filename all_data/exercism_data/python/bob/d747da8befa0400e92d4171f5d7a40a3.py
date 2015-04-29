def nothing(arg):
    return arg is None or arg.strip() == ''


def shouting(arg):
    return arg.isupper()


def question(arg):
    return arg.strip().endswith('?')


def hey(arg=None):
    response = 'Whatever.'
    if nothing(arg):
        response = 'Fine. Be that way!'
    elif shouting(arg):
        response = 'Whoa, chill out!'
    elif question(arg):
        response = 'Sure.'
    return response
